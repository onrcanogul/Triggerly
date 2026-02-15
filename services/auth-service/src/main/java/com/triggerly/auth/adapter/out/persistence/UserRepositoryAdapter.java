package com.triggerly.auth.adapter.out.persistence;

import com.triggerly.auth.application.port.out.UserRepositoryPort;
import com.triggerly.auth.domain.model.User;
import com.triggerly.auth.domain.model.valueobject.Email;
import com.triggerly.auth.domain.model.valueobject.Password;
import com.triggerly.auth.domain.model.valueobject.UserId;
import com.triggerly.auth.domain.model.valueobject.Username;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserRepositoryAdapter implements UserRepositoryPort {

    private final UserJpaRepository jpaRepository;

    public UserRepositoryAdapter(UserJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public User save(User user) {
        UserJpaEntity entity = toEntity(user);
        UserJpaEntity saved = jpaRepository.save(entity);
        return toDomain(saved);
    }

    @Override
    public Optional<User> findById(UserId userId) {
        return jpaRepository.findById(userId.value())
                .map(this::toDomain);
    }

    @Override
    public Optional<User> findByEmail(Email email) {
        return jpaRepository.findByEmail(email.value())
                .map(this::toDomain);
    }

    @Override
    public Optional<User> findByUsername(Username username) {
        return jpaRepository.findByUsername(username.value())
                .map(this::toDomain);
    }

    @Override
    public boolean existsByEmail(Email email) {
        return jpaRepository.existsByEmail(email.value());
    }

    @Override
    public boolean existsByUsername(Username username) {
        return jpaRepository.existsByUsername(username.value());
    }

    @Override
    public void delete(User user) {
        jpaRepository.deleteById(user.getId().value());
    }

    private UserJpaEntity toEntity(User user) {
        return new UserJpaEntity(
                user.getId().value(),
                user.getUsername().value(),
                user.getEmail().value(),
                user.getPassword().value(),
                user.getCreatedAt(),
                user.getUpdatedAt(),
                user.isDeleted()
        );
    }

    private User toDomain(UserJpaEntity entity) {
        return User.reconstitute(
                UserId.of(entity.getId()),
                new Username(entity.getUsername()),
                new Email(entity.getEmail()),
                Password.hashed(entity.getPassword()),
                entity.getCreatedAt(),
                entity.getUpdatedAt(),
                entity.isDeleted()
        );
    }
}

