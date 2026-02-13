package com.triggerly.market.application.port.out;

import triggerly.common.event.domain.BaseDomainEvent;

public interface DomainEventBusPort {
    void publish(BaseDomainEvent event, String runId);
}
