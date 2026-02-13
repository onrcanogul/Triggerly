package com.triggerly.market.config;

import com.triggerly.market.adapter.out.external.DummyMarketDataProviderAdapter;
import com.triggerly.market.adapter.out.messaging.KafkaMarketEventPublisherAdapter;
import com.triggerly.market.adapter.out.messaging.LogDomainEventBusAdapter;
import com.triggerly.market.application.port.out.DomainEventBusPort;
import com.triggerly.market.application.port.out.MarketDataProviderPort;
import com.triggerly.market.application.port.out.MarketEventPublisherPort;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import triggerly.contracts.Envelope;

public class WiringConfig {
    @Bean
    public DomainEventBusPort domainEventBusPort() {
        return new LogDomainEventBusAdapter();
    }

    @Bean
    public MarketEventPublisherPort marketEventPublisherPort(KafkaTemplate<String, Envelope<?>> kafkaTemplate) {
        return new KafkaMarketEventPublisherAdapter(kafkaTemplate, "market-service");
    }

    @Bean
    public MarketDataProviderPort marketDataProviderPort() {
        return new DummyMarketDataProviderAdapter();
    }
}
