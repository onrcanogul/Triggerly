package com.triggerly.auth.application.port.in;

import com.triggerly.auth.application.dto.UserInfoResponse;

public interface GetUserInfoUseCase {
    UserInfoResponse execute(String userId);
}

