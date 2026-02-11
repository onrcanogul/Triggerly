package com.triggerly.market.domain.model.valueobjects;

import java.math.BigDecimal;
import java.time.Instant;

public final class PriceTick {

    private final Symbol symbol;
    private final BigDecimal price;
    private final Instant asOf;

    public PriceTick(Symbol symbol, BigDecimal price, Instant asOf) {
        if (price == null || price.signum() <= 0) {
            throw new IllegalArgumentException("Price must be positive");
        }
        this.symbol = symbol;
        this.price = price;
        this.asOf = asOf;
    }

    public Symbol symbol() {
        return symbol;
    }

    public BigDecimal price() {
        return price;
    }

    public Instant asOf() {
        return asOf;
    }
}
