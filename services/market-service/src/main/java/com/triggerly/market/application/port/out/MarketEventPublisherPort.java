package com.triggerly.market.application.port.out;

import com.triggerly.market.domain.model.MarketSnapshot;

public interface MarketEventPublisherPort {
    void publish(MarketSnapshot snapshot, String runId);
}