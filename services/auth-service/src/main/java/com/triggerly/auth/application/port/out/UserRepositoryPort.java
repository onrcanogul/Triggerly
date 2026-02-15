package com.triggerly.auth.application.port.out;

import com.triggerly.auth.domain.model.User;
import com.triggerly.auth.domain.model.valueobject.Email;
import com.triggerly.auth.domain.model.valueobject.UserId;
import com.triggerly.auth.domain.model.valueobject.Username;

import java.util.Optional;

public interface UserRepositoryPort {
    User save(User user);
    Optional<User> findById(UserId userId);
    Optional<User> findByEmail(Email email);
    Optional<User> findByUsername(Username username);
    boolean existsByEmail(Email email);
    boolean existsByUsername(Username username);
    void delete(User user);
}

