package com.triggerly.auth.application.port.in;

import com.triggerly.auth.application.dto.RegisterRequest;
import com.triggerly.auth.application.dto.RegisterResponse;

public interface RegisterUserUseCase {
    RegisterResponse execute(RegisterRequest request);
}

