package com.triggerly.market.adapter.in.rest;

import com.triggerly.market.adapter.in.rest.dto.PublishRequest;
import com.triggerly.market.application.dto.MarketSnapshotRequest;
import com.triggerly.market.application.dto.PublishResult;
import com.triggerly.market.application.port.in.PublishMarketSnapshotsUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/internal/market")
public class MarketInternalController {

    private final PublishMarketSnapshotsUseCase useCase;

    public MarketInternalController(PublishMarketSnapshotsUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping("/publish")
    public ResponseEntity<PublishResult> publish(@RequestBody PublishRequest request,
                                                 @RequestHeader(value = "X-Run-Id", required = false) String runIdHeader) {
        PublishResult result = useCase.execute(new MarketSnapshotRequest(request.symbols(), runIdHeader));
        return ResponseEntity.ok(result);
    }
}