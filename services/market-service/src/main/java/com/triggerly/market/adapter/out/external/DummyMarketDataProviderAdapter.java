package com.triggerly.market.adapter.out.external;

import com.triggerly.market.application.port.out.MarketDataProviderPort;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Component
public class DummyMarketDataProviderAdapter implements MarketDataProviderPort {
    @Override
    public Map<String, RawMarketData> fetchForSymbols(Iterable<String> symbols) {
        Map<String, RawMarketData> map = new HashMap<>();
        for (String s : symbols) {
            map.put(s, new RawMarketData(
                    s,
                    new BigDecimal("123.45"),
                    new BigDecimal("1.23"),
                    new BigDecimal("999999"),
                    "DUMMY"
            ));
        }
        return map;
    }
}
