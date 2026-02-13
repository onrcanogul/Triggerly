package com.triggerly.market.application.dto;

import java.util.List;

public record PublishResult(
        String runId,
        int requestedSymbolCount,
        int fetchedSymbolCount,
        int publishedCount,
        List<String> missingSymbols
) {
}
