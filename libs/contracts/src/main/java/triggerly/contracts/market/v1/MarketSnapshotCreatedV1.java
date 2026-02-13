package triggerly.contracts.market.v1;

import java.math.BigDecimal;
import java.time.Instant;

public record MarketSnapshotCreatedV1(
        String symbol,
        BigDecimal price,
        Instant asOf,
        BigDecimal change24h,
        BigDecimal volume24h,
        String source
) { }
