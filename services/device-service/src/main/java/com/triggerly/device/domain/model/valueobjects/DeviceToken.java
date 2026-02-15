package com.triggerly.device.domain.model.valueobjects;

import java.util.Objects;

public final class DeviceToken {

    private final String value;

    public DeviceToken(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("DeviceToken cannot be empty");
        }
        this.value = value;
    }

    public String value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DeviceToken that)) return false;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}

