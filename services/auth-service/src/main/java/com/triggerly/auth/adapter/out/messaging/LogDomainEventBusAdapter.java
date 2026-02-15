package com.triggerly.auth.adapter.out.messaging;

import com.triggerly.auth.application.port.out.DomainEventBusPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import triggerly.common.event.domain.BaseDomainEvent;

@Component
public class LogDomainEventBusAdapter implements DomainEventBusPort {

    private static final Logger log = LoggerFactory.getLogger(LogDomainEventBusAdapter.class);

    @Override
    public void publish(BaseDomainEvent event, String correlationId) {
        log.info("Domain event published: {} | correlationId: {} | eventId: {}",
                event.getClass().getSimpleName(),
                correlationId,
                event.getEventId());
    }
}

