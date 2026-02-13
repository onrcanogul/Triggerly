package com.triggerly.market.adapter.out.messaging;

import com.triggerly.market.application.port.out.MarketEventPublisherPort;
import com.triggerly.market.domain.model.MarketSnapshot;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import triggerly.contracts.Envelope;
import triggerly.contracts.Topics;
import triggerly.contracts.market.v1.MarketSnapshotCreatedV1;

@Component
public class KafkaMarketEventPublisherAdapter implements MarketEventPublisherPort {

    private final KafkaTemplate<String, Envelope<?>> kafkaTemplate;
    private final String producerName;

    public KafkaMarketEventPublisherAdapter(KafkaTemplate<String, Envelope<?>> kafkaTemplate, String producerName) {
        this.kafkaTemplate = kafkaTemplate;
        this.producerName = producerName;
    }

    @Override
    public void publish(MarketSnapshot snapshot, String runId) {
        String symbol = snapshot.tick().symbol().value(); // partition key

        MarketSnapshotCreatedV1 payload = new MarketSnapshotCreatedV1(
                symbol,
                snapshot.tick().price(),
                snapshot.tick().asOf(),
                snapshot.change24h(),
                snapshot.volume24h(),
                snapshot.source()
        );

        Envelope<MarketSnapshotCreatedV1> envelope = Envelope.of(producerName, MarketSnapshotCreatedV1.class.getTypeName(), 1, runId, payload);

        kafkaTemplate.send(Topics.MARKET_SNAPSHOT_CREATED_V1, symbol, envelope);
    }
}
