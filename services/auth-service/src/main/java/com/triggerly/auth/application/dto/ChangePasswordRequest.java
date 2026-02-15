package com.triggerly.auth.application.dto;

public record ChangePasswordRequest(
        String currentPassword,
        String newPassword
) {
}

