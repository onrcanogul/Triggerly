package com.triggerly.market.adapter.out.messaging;

import com.triggerly.market.application.port.out.DomainEventBusPort;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import triggerly.common.event.domain.BaseDomainEvent;


public class LogDomainEventBusAdapter implements DomainEventBusPort {

    private static final Logger log = LoggerFactory.getLogger(LogDomainEventBusAdapter.class);

    @Override
    public void publish(BaseDomainEvent event, String runId) {
        log.info("[runId={}] domainEvent={} eventId={} occurredAt={}",
                runId,
                event.getClass().getSimpleName(),
                event.getEventId(),
                event.getOccurredAt()
        );
    }
}
