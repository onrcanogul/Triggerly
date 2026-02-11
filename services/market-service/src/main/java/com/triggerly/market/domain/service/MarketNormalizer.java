package com.triggerly.market.domain.service;

import com.triggerly.market.domain.model.MarketSnapshot;
import com.triggerly.market.domain.model.valueobjects.PriceTick;
import com.triggerly.market.domain.model.valueobjects.Symbol;

import java.math.BigDecimal;
import java.time.Instant;

public class MarketNormalizer {

    public MarketSnapshot normalize(
            String rawSymbol,
            BigDecimal rawPrice,
            BigDecimal rawChange24h,
            BigDecimal rawVolume,
            String source
    ) {
        Symbol symbol = new Symbol(rawSymbol);

        PriceTick priceTick = new PriceTick(symbol, rawPrice, Instant.now());

        return new MarketSnapshot(priceTick, rawChange24h, rawVolume, source);
    }
}
