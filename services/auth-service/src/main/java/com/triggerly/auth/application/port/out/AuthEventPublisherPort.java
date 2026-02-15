package com.triggerly.auth.application.port.out;

import java.time.Instant;

public interface AuthEventPublisherPort {
    void publishUserRegistered(String userId, String email, String username, Instant registeredAt, String correlationId);
    void publishUserLoggedIn(String userId, String email, Instant loginAt, String ipAddress, String correlationId);
    void publishUserDeleted(String userId, String email, Instant deletedAt, String correlationId);
}

