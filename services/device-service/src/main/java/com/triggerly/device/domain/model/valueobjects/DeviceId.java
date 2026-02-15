package com.triggerly.device.domain.model.valueobjects;

import java.util.Objects;

public final class DeviceId {

    private final String value;

    public DeviceId(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("DeviceId cannot be empty");
        }
        this.value = value;
    }

    public String value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DeviceId deviceId)) return false;
        return Objects.equals(value, deviceId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value;
    }
}

