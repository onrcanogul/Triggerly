package com.triggerly.auth.application.usecase;

import com.triggerly.auth.application.dto.RegisterRequest;
import com.triggerly.auth.application.dto.RegisterResponse;
import com.triggerly.auth.application.port.in.RegisterUserUseCase;
import com.triggerly.auth.application.port.out.AuthEventPublisherPort;
import com.triggerly.auth.application.port.out.DomainEventBusPort;
import com.triggerly.auth.application.port.out.PasswordHashPort;
import com.triggerly.auth.application.port.out.TokenGeneratorPort;
import com.triggerly.auth.application.port.out.UserRepositoryPort;
import com.triggerly.auth.domain.event.UserRegisteredEvent;
import com.triggerly.auth.domain.exception.UserAlreadyExistsException;
import com.triggerly.auth.domain.model.User;
import com.triggerly.auth.domain.model.valueobject.Email;
import com.triggerly.auth.domain.model.valueobject.Password;
import com.triggerly.auth.domain.model.valueobject.Username;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class RegisterUserService implements RegisterUserUseCase {

    private final UserRepositoryPort userRepository;
    private final PasswordHashPort passwordHash;
    private final TokenGeneratorPort tokenGenerator;
    private final AuthEventPublisherPort authEventPublisher;
    private final DomainEventBusPort domainEventBus;

    public RegisterUserService(
            UserRepositoryPort userRepository,
            PasswordHashPort passwordHash,
            TokenGeneratorPort tokenGenerator,
            AuthEventPublisherPort authEventPublisher,
            DomainEventBusPort domainEventBus
    ) {
        this.userRepository = userRepository;
        this.passwordHash = passwordHash;
        this.tokenGenerator = tokenGenerator;
        this.authEventPublisher = authEventPublisher;
        this.domainEventBus = domainEventBus;
    }

    @Override
    @Transactional
    public RegisterResponse execute(RegisterRequest request) {
        Email email = new Email(request.email());
        Username username = new Username(request.username());

        if (userRepository.existsByEmail(email)) {
            throw new UserAlreadyExistsException("Email already registered: " + email.value());
        }

        if (userRepository.existsByUsername(username)) {
            throw new UserAlreadyExistsException("Username already taken: " + username.value());
        }

        Password rawPassword = Password.raw(request.password());
        String hashedPasswordValue = passwordHash.hash(rawPassword.value());
        Password hashedPassword = Password.hashed(hashedPasswordValue);

        User user = User.create(username, email, hashedPassword);

        User savedUser = userRepository.save(user);

        String correlationId = UUID.randomUUID().toString();

        savedUser.getDomainEvents().forEach(event -> {
            domainEventBus.publish(event, correlationId);
        });

        savedUser.clearDomainEvents();

        authEventPublisher.publishUserRegistered(
                savedUser.getId().value(),
                savedUser.getEmail().value(),
                savedUser.getUsername().value(),
                savedUser.getCreatedAt(),
                correlationId
        );

        String token = tokenGenerator.generateToken(
                savedUser.getId().value(),
                savedUser.getEmail().value(),
                savedUser.getUsername().value()
        );

        return new RegisterResponse(
                savedUser.getId().value(),
                savedUser.getUsername().value(),
                savedUser.getEmail().value(),
                token,
                savedUser.getCreatedAt()
        );
    }
}

