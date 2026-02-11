package com.triggerly.market.domain.model.valueobjects;

import java.util.Objects;

public final class Symbol {

    private final String value;

    public Symbol(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Symbol cannot be empty");
        }
        this.value = value.toUpperCase();
    }

    public String value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Symbol symbol)) return false;
        return Objects.equals(value, symbol.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
