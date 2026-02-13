package com.triggerly.market.domain.event;

import com.triggerly.market.domain.model.valueobjects.Symbol;
import triggerly.common.event.domain.BaseDomainEvent;

import java.math.BigDecimal;

public class SignificantPriceChangeDetectedEvent extends BaseDomainEvent {

    private final Symbol symbol;
    private final BigDecimal oldPrice;
    private final BigDecimal newPrice;
    private final BigDecimal percentageChange;

    public SignificantPriceChangeDetectedEvent(Symbol symbol,
                                               BigDecimal oldPrice,
                                               BigDecimal newPrice,
                                               BigDecimal percentageChange) {
        this.symbol = symbol;
        this.oldPrice = oldPrice;
        this.newPrice = newPrice;
        this.percentageChange = percentageChange;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public BigDecimal getOldPrice() {
        return oldPrice;
    }

    public BigDecimal getNewPrice() {
        return newPrice;
    }

    public BigDecimal getPercentageChange() {
        return percentageChange;
    }
}
