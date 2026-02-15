package com.triggerly.auth.application.port.in;

import com.triggerly.auth.application.dto.ChangePasswordRequest;

public interface ChangePasswordUseCase {
    void execute(String userId, ChangePasswordRequest request);
}

