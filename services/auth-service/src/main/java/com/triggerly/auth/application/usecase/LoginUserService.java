package com.triggerly.auth.application.usecase;

import com.triggerly.auth.application.dto.LoginRequest;
import com.triggerly.auth.application.dto.LoginResponse;
import com.triggerly.auth.application.port.in.LoginUserUseCase;
import com.triggerly.auth.application.port.out.AuthEventPublisherPort;
import com.triggerly.auth.application.port.out.DomainEventBusPort;
import com.triggerly.auth.application.port.out.PasswordHashPort;
import com.triggerly.auth.application.port.out.TokenGeneratorPort;
import com.triggerly.auth.application.port.out.UserRepositoryPort;
import com.triggerly.auth.domain.event.UserLoggedInEvent;
import com.triggerly.auth.domain.exception.InvalidCredentialsException;
import com.triggerly.auth.domain.exception.UserNotFoundException;
import com.triggerly.auth.domain.model.User;
import com.triggerly.auth.domain.model.valueobject.Email;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
public class LoginUserService implements LoginUserUseCase {

    private final UserRepositoryPort userRepository;
    private final PasswordHashPort passwordHash;
    private final TokenGeneratorPort tokenGenerator;
    private final AuthEventPublisherPort authEventPublisher;
    private final DomainEventBusPort domainEventBus;

    public LoginUserService(
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
    public LoginResponse execute(LoginRequest request, String ipAddress) {
        Email email = new Email(request.email());

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + email.value()));

        if (user.isDeleted()) {
            throw new UserNotFoundException("User not found with email: " + email.value());
        }

        if (!passwordHash.verify(request.password(), user.getPassword().value())) {
            throw new InvalidCredentialsException("Invalid credentials");
        }

        Instant loginAt = Instant.now();
        String correlationId = UUID.randomUUID().toString();

        UserLoggedInEvent loginEvent = new UserLoggedInEvent(
                user,
                loginAt,
                ipAddress,
                "web"
        );

        domainEventBus.publish(loginEvent, correlationId);

        authEventPublisher.publishUserLoggedIn(
                user.getId().value(),
                user.getEmail().value(),
                loginAt,
                ipAddress,
                correlationId
        );

        String token = tokenGenerator.generateToken(
                user.getId().value(),
                user.getEmail().value(),
                user.getUsername().value()
        );

        return new LoginResponse(
                user.getId().value(),
                user.getUsername().value(),
                user.getEmail().value(),
                token,
                loginAt
        );
    }
}

