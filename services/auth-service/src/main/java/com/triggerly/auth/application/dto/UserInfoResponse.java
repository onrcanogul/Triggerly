package com.triggerly.auth.application.dto;

import java.time.Instant;

public record UserInfoResponse(
        String userId,
        String username,
        String email,
        Instant createdAt,
        Instant updatedAt
) {
}

