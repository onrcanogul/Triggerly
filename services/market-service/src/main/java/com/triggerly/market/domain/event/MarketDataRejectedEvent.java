package com.triggerly.market.domain.event;

import triggerly.common.event.domain.BaseDomainEvent;

public class MarketDataRejectedEvent extends BaseDomainEvent {

    private final String rawSymbol;
    private final String reason;
    private final String source;

    public MarketDataRejectedEvent(String rawSymbol,
                                   String reason,
                                   String source) {
        this.rawSymbol = rawSymbol;
        this.reason = reason;
        this.source = source;
    }

    public String getRawSymbol() {
        return rawSymbol;
    }

    public String getReason() {
        return reason;
    }

    public String getSource() {
        return source;
    }
}
