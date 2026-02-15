package com.triggerly.auth.application.usecase;

import com.triggerly.auth.application.port.in.DeleteUserUseCase;
import com.triggerly.auth.application.port.out.AuthEventPublisherPort;
import com.triggerly.auth.application.port.out.DomainEventBusPort;
import com.triggerly.auth.application.port.out.UserRepositoryPort;
import com.triggerly.auth.domain.event.UserDeletedEvent;
import com.triggerly.auth.domain.exception.UserNotFoundException;
import com.triggerly.auth.domain.model.User;
import com.triggerly.auth.domain.model.valueobject.UserId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class DeleteUserService implements DeleteUserUseCase {

    private final UserRepositoryPort userRepository;
    private final AuthEventPublisherPort authEventPublisher;
    private final DomainEventBusPort domainEventBus;

    public DeleteUserService(
            UserRepositoryPort userRepository,
            AuthEventPublisherPort authEventPublisher,
            DomainEventBusPort domainEventBus
    ) {
        this.userRepository = userRepository;
        this.authEventPublisher = authEventPublisher;
        this.domainEventBus = domainEventBus;
    }

    @Override
    @Transactional
    public void execute(String userId) {
        UserId id = UserId.of(userId);

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (user.isDeleted()) {
            throw new UserNotFoundException("User not found");
        }

        user.markAsDeleted();

        User savedUser = userRepository.save(user);

        String correlationId = UUID.randomUUID().toString();

        savedUser.getDomainEvents().forEach(event -> {
            domainEventBus.publish(event, correlationId);
        });

        savedUser.clearDomainEvents();

        authEventPublisher.publishUserDeleted(
                savedUser.getId().value(),
                savedUser.getEmail().value(),
                savedUser.getUpdatedAt(),
                correlationId
        );
    }
}

