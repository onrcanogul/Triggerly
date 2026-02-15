package com.triggerly.auth.application.dto;

import java.time.Instant;

public record RegisterResponse(
        String userId,
        String username,
        String email,
        String token,
        Instant createdAt
) {
}

