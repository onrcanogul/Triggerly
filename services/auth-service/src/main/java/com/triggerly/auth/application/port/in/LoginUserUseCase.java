package com.triggerly.auth.application.port.in;

import com.triggerly.auth.application.dto.LoginRequest;
import com.triggerly.auth.application.dto.LoginResponse;

public interface LoginUserUseCase {
    LoginResponse execute(LoginRequest request, String ipAddress);
}

