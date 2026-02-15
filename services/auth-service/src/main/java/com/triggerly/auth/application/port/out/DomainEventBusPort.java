package com.triggerly.auth.application.port.out;

import triggerly.common.event.domain.BaseDomainEvent;

public interface DomainEventBusPort {
    void publish(BaseDomainEvent event, String correlationId);
}

