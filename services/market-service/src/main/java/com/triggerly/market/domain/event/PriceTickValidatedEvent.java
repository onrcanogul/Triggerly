package com.triggerly.market.domain.event;

import com.triggerly.market.domain.model.valueobjects.Symbol;
import triggerly.common.event.domain.BaseDomainEvent;

import java.math.BigDecimal;
import java.time.Instant;

public class PriceTickValidatedEvent extends BaseDomainEvent {

    private final Symbol symbol;
    private final BigDecimal price;
    private final Instant validatedAt;

    public PriceTickValidatedEvent(Symbol symbol,
                                   BigDecimal price,
                                   Instant validatedAt) {
        this.symbol = symbol;
        this.price = price;
        this.validatedAt = validatedAt;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Instant getValidatedAt() {
        return validatedAt;
    }
}
