package com.triggerly.auth.domain.model.valueobject;

public record Password(String value) {

    public Password {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Password cannot be null or blank");
        }
    }

    public static Password raw(String rawPassword) {
        if (rawPassword == null || rawPassword.isBlank()) {
            throw new IllegalArgumentException("Password cannot be null or blank");
        }
        if (rawPassword.length() < 8) {
            throw new IllegalArgumentException("Password must be at least 8 characters");
        }
        return new Password(rawPassword);
    }

    public static Password hashed(String hashedPassword) {
        return new Password(hashedPassword);
    }
}

