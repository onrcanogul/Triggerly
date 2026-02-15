package com.triggerly.auth.domain.model.valueobject;

import triggerly.common.entity.BaseEntity;

import java.util.UUID;

public class UserId extends BaseEntity<UUID> {

    private UserId(UUID id) {
        super(id);
    }

    public static UserId generate() {
        return new UserId(UUID.randomUUID());
    }

    public static UserId of(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("UserId cannot be null");
        }
        return new UserId(id);
    }

    public static UserId of(String id) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("UserId cannot be null or blank");
        }
        return new UserId(UUID.fromString(id));
    }

    public String value() {
        return id.toString();
    }
}

