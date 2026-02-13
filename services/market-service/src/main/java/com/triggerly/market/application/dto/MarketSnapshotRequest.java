package com.triggerly.market.application.dto;

import java.util.List;

public record MarketSnapshotRequest (List<String> symbols, String runId) {
}
