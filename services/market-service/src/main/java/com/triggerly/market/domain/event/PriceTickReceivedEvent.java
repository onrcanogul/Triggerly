package com.triggerly.market.domain.event;

import com.triggerly.market.domain.model.valueobjects.Symbol;
import triggerly.common.event.domain.BaseDomainEvent;

import java.math.BigDecimal;
import java.time.Instant;

public class PriceTickReceivedEvent extends BaseDomainEvent {

    private final Symbol symbol;
    private final BigDecimal price;
    private final Instant asOf;
    private final String source;

    public PriceTickReceivedEvent(Symbol symbol,
                                  BigDecimal price,
                                  Instant asOf,
                                  String source) {
        this.symbol = symbol;
        this.price = price;
        this.asOf = asOf;
        this.source = source;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Instant getAsOf() {
        return asOf;
    }

    public String getSource() {
        return source;
    }
}

