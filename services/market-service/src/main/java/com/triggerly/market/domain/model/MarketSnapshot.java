package com.triggerly.market.domain.model;

import com.triggerly.market.domain.model.valueobjects.PriceTick;

import java.math.BigDecimal;

public final class MarketSnapshot {

    private final PriceTick tick;
    private final BigDecimal change24h;
    private final BigDecimal volume24h;
    private final String source;

    public MarketSnapshot(
            PriceTick tick,
            BigDecimal change24h,
            BigDecimal volume24h,
            String source
    ) {
        this.tick = tick;
        this.change24h = change24h;
        this.volume24h = volume24h;
        this.source = source;
    }

    public PriceTick tick() {
        return tick;
    }

    public BigDecimal change24h() {
        return change24h;
    }

    public BigDecimal volume24h() {
        return volume24h;
    }

    public String source() {
        return source;
    }
}

