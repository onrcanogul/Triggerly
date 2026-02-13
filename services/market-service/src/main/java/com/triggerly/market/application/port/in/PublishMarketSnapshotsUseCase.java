package com.triggerly.market.application.port.in;

import com.triggerly.market.application.dto.MarketSnapshotRequest;
import com.triggerly.market.application.dto.PublishResult;

public interface PublishMarketSnapshotsUseCase {
    PublishResult execute(MarketSnapshotRequest request);
}
