package com.triggerly.auth.application.usecase;

import com.triggerly.auth.application.dto.UserInfoResponse;
import com.triggerly.auth.application.port.in.GetUserInfoUseCase;
import com.triggerly.auth.application.port.out.UserRepositoryPort;
import com.triggerly.auth.domain.exception.UserNotFoundException;
import com.triggerly.auth.domain.model.User;
import com.triggerly.auth.domain.model.valueobject.UserId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GetUserInfoService implements GetUserInfoUseCase {

    private final UserRepositoryPort userRepository;

    public GetUserInfoService(UserRepositoryPort userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserInfoResponse execute(String userId) {
        UserId id = UserId.of(userId);

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (user.isDeleted()) {
            throw new UserNotFoundException("User not found");
        }

        return new UserInfoResponse(
                user.getId().value(),
                user.getUsername().value(),
                user.getEmail().value(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }
}

