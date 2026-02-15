package com.triggerly.auth.application.usecase;

import com.triggerly.auth.application.dto.ChangePasswordRequest;
import com.triggerly.auth.application.port.in.ChangePasswordUseCase;
import com.triggerly.auth.application.port.out.PasswordHashPort;
import com.triggerly.auth.application.port.out.UserRepositoryPort;
import com.triggerly.auth.domain.exception.InvalidCredentialsException;
import com.triggerly.auth.domain.exception.UserNotFoundException;
import com.triggerly.auth.domain.model.User;
import com.triggerly.auth.domain.model.valueobject.Password;
import com.triggerly.auth.domain.model.valueobject.UserId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ChangePasswordService implements ChangePasswordUseCase {

    private final UserRepositoryPort userRepository;
    private final PasswordHashPort passwordHash;

    public ChangePasswordService(UserRepositoryPort userRepository, PasswordHashPort passwordHash) {
        this.userRepository = userRepository;
        this.passwordHash = passwordHash;
    }

    @Override
    @Transactional
    public void execute(String userId, ChangePasswordRequest request) {
        UserId id = UserId.of(userId);

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (user.isDeleted()) {
            throw new UserNotFoundException("User not found");
        }

        if (!passwordHash.verify(request.currentPassword(), user.getPassword().value())) {
            throw new InvalidCredentialsException("Current password is incorrect");
        }

        Password newRawPassword = Password.raw(request.newPassword());
        String newHashedPasswordValue = passwordHash.hash(newRawPassword.value());
        Password newHashedPassword = Password.hashed(newHashedPasswordValue);

        user.updatePassword(newHashedPassword);

        userRepository.save(user);
    }
}

