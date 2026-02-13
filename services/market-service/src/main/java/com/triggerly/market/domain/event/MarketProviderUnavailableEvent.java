package com.triggerly.market.domain.event;

import triggerly.common.event.domain.BaseDomainEvent;

public class MarketProviderUnavailableEvent extends BaseDomainEvent {

    private final String source;
    private final String errorMessage;

    public MarketProviderUnavailableEvent(String source,
                                          String errorMessage) {
        this.source = source;
        this.errorMessage = errorMessage;
    }

    public String getSource() {
        return source;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
