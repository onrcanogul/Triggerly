package com.triggerly.auth.application.port.out;

public interface PasswordHashPort {
    String hash(String rawPassword);
    boolean verify(String rawPassword, String hashedPassword);
}

