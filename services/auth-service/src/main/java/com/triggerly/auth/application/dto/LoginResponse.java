package com.triggerly.auth.application.dto;

import java.time.Instant;

public record LoginResponse(
        String userId,
        String username,
        String email,
        String token,
        Instant loginAt
) {
}

