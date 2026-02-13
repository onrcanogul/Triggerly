package com.triggerly.market.domain.event;

import com.triggerly.market.domain.model.MarketSnapshot;
import triggerly.common.event.domain.BaseDomainEvent;

public class MarketSnapshotCreatedEvent extends BaseDomainEvent {

    private final MarketSnapshot snapshot;

    public MarketSnapshotCreatedEvent(MarketSnapshot snapshot) {
        this.snapshot = snapshot;
    }

    public MarketSnapshot getSnapshot() {
        return snapshot;
    }
}
