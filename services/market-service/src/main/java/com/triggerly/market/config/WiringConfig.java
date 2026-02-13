package com.triggerly.market.config;

import com.triggerly.market.adapter.out.messaging.LogDomainEventBusAdapter;
import com.triggerly.market.application.port.out.DomainEventBusPort;
import org.springframework.context.annotation.Bean;

public class WiringConfig {
    @Bean
    public DomainEventBusPort domainEventBusPort() {
        return new LogDomainEventBusAdapter();
    }
}
