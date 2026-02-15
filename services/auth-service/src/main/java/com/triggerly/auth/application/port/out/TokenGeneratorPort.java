package com.triggerly.auth.application.port.out;

public interface TokenGeneratorPort {
    String generateToken(String userId, String email, String username);
    boolean validateToken(String token);
    String extractUserId(String token);
}

