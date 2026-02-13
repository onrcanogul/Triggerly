package com.triggerly.market.application.port.out;

import java.math.BigDecimal;
import java.util.Map;

public interface MarketDataProviderPort {

    record RawMarketData(
            String symbol,
            BigDecimal price,
            BigDecimal change24h,
            BigDecimal volume24h,
            String source
    ) {}

    Map<String, RawMarketData> fetchForSymbols(Iterable<String> symbols);
}
