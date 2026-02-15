package com.triggerly.auth.adapter.out.messaging;

import com.triggerly.auth.application.port.out.AuthEventPublisherPort;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import triggerly.contracts.Envelope;
import triggerly.contracts.Topics;
import triggerly.contracts.auth.v1.UserDeletedV1;
import triggerly.contracts.auth.v1.UserLoggedInV1;
import triggerly.contracts.auth.v1.UserRegisteredV1;

import java.time.Instant;
import java.util.UUID;

@Component
public class KafkaAuthEventPublisherAdapter implements AuthEventPublisherPort {

    private final KafkaTemplate<String, Envelope<?>> kafkaTemplate;
    private final String producerName;

    public KafkaAuthEventPublisherAdapter(KafkaTemplate<String, Envelope<?>> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.producerName = "auth-service";
    }

    @Override
    public void publishUserRegistered(String userId, String email, String username, Instant registeredAt, String correlationId) {
        UserRegisteredV1 payload = new UserRegisteredV1(
                userId,
                email,
                username,
                registeredAt
        );

        Envelope<UserRegisteredV1> envelope = new Envelope<>(
                UUID.randomUUID(),
                Instant.now(),
                correlationId,
                1,
                producerName,
                Topics.USER_REGISTERED_V1,
                payload
        );

        kafkaTemplate.send(Topics.USER_REGISTERED_V1, userId, envelope);
    }

    @Override
    public void publishUserLoggedIn(String userId, String email, Instant loginAt, String ipAddress, String correlationId) {
        UserLoggedInV1 payload = new UserLoggedInV1(
                userId,
                email,
                loginAt,
                ipAddress
        );

        Envelope<UserLoggedInV1> envelope = new Envelope<>(
                UUID.randomUUID(),
                Instant.now(),
                correlationId,
                1,
                producerName,
                Topics.USER_LOGGED_IN_V1,
                payload
        );

        kafkaTemplate.send(Topics.USER_LOGGED_IN_V1, userId, envelope);
    }

    @Override
    public void publishUserDeleted(String userId, String email, Instant deletedAt, String correlationId) {
        UserDeletedV1 payload = new UserDeletedV1(
                userId,
                email,
                deletedAt
        );

        Envelope<UserDeletedV1> envelope = new Envelope<>(
                UUID.randomUUID(),
                Instant.now(),
                correlationId,
                1,
                producerName,
                Topics.USER_DELETED_V1,
                payload
        );

        kafkaTemplate.send(Topics.USER_DELETED_V1, userId, envelope);
    }
}

