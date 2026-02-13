package com.triggerly.market.application.usecase;

import com.triggerly.market.application.dto.MarketSnapshotRequest;
import com.triggerly.market.application.dto.PublishResult;
import com.triggerly.market.application.port.in.PublishMarketSnapshotsUseCase;
import com.triggerly.market.application.port.out.DomainEventBusPort;
import com.triggerly.market.application.port.out.MarketDataProviderPort;
import com.triggerly.market.application.port.out.MarketEventPublisherPort;
import com.triggerly.market.domain.event.MarketSnapshotCreatedEvent;
import com.triggerly.market.domain.model.MarketSnapshot;
import com.triggerly.market.domain.service.MarketNormalizer;

import java.util.*;


public class PublishMarketSnapshotsService implements PublishMarketSnapshotsUseCase {

    private final MarketDataProviderPort provider;
    private final MarketNormalizer normalizer;
    private final MarketEventPublisherPort publisher;
    private final DomainEventBusPort domainEventBusPublisher;

    public PublishMarketSnapshotsService(MarketDataProviderPort provider, MarketNormalizer normalizer, MarketEventPublisherPort publisher, DomainEventBusPort domainEventBusPublisher) {
        this.provider = provider;
        this.normalizer = normalizer;
        this.publisher = publisher;
        this.domainEventBusPublisher = domainEventBusPublisher;
    }

    @Override
    public PublishResult execute(MarketSnapshotRequest request) {
        List<String> symbols = normalizeSymbols(request.symbols());
        String runId = (request.runId() == null || request.runId().isBlank()) ? UUID.randomUUID().toString() : request.runId();

        if (symbols.isEmpty()) {
            return new PublishResult(runId, 0, 0, 0, List.of());
        }

        Map<String, MarketDataProviderPort.RawMarketData> rawMap = provider.fetchForSymbols(symbols);

        int published = 0;
        List<String> missing = new ArrayList<>();

        for (String symbol: symbols) {
            MarketDataProviderPort.RawMarketData raw = rawMap.get(symbol);

            if (raw == null) {
                missing.add(symbol);
                continue;
            }

            MarketSnapshot snapshot = normalizer.normalize(raw.symbol(), raw.price(), raw.change24h(), raw.volume24h(), raw.source());

            //DUAL WRITE BUT JUST LOGGING.
            domainEventBusPublisher.publish(new MarketSnapshotCreatedEvent(snapshot), runId);
            publisher.publish(snapshot, runId);
            published++;
        }

        return new PublishResult(
                runId,
                symbols.size(),
                rawMap.size(),
                published,
                missing
        );

    }



    private static List<String> normalizeSymbols(List<String> symbols) {
        if (symbols == null) return List.of();
        return symbols.stream()
                .filter(Objects::nonNull)
                .map(String::trim)
                .filter(s -> !s.isBlank())
                .map(String::toUpperCase)
                .distinct()
                .toList();
    }
}
