package com.triggerly.device.domain.model;

import com.triggerly.device.domain.event.*;
import com.triggerly.device.domain.model.valueobjects.*;
import triggerly.common.ddd.AggregateRoot;

import java.time.Instant;

public class Device extends AggregateRoot<DeviceId> {

    private UserId userId;
    private DeviceType deviceType;
    private DeviceStatus status;
    private DeviceToken token;
    private DeviceMetadata metadata;
    private Instant lastActiveAt;
    private Instant createdAt;
    private Instant updatedAt;

    private Device(DeviceId id) {
        super(id);
    }

    public static Device register(
            DeviceId deviceId,
            UserId userId,
            DeviceType deviceType,
            DeviceToken token,
            DeviceMetadata metadata
    ) {
        Device device = new Device(deviceId);
        device.userId = userId;
        device.deviceType = deviceType;
        device.token = token;
        device.metadata = metadata;
        device.status = DeviceStatus.ACTIVE;
        device.createdAt = Instant.now();
        device.updatedAt = Instant.now();
        device.lastActiveAt = Instant.now();

        device.registerEvent(new DeviceRegisteredEvent(deviceId, userId, deviceType));

        return device;
    }

    public void activate() {
        if (this.status == DeviceStatus.ACTIVE) {
            throw new IllegalStateException("Device is already active");
        }
        this.status = DeviceStatus.ACTIVE;
        this.updatedAt = Instant.now();
        this.lastActiveAt = Instant.now();

        registerEvent(new DeviceActivatedEvent(this.id));
    }

    public void deactivate() {
        if (this.status == DeviceStatus.INACTIVE) {
            throw new IllegalStateException("Device is already inactive");
        }
        this.status = DeviceStatus.INACTIVE;
        this.updatedAt = Instant.now();

        registerEvent(new DeviceDeactivatedEvent(this.id));
    }

    public void suspend(String reason) {
        if (this.status == DeviceStatus.SUSPENDED) {
            throw new IllegalStateException("Device is already suspended");
        }
        this.status = DeviceStatus.SUSPENDED;
        this.updatedAt = Instant.now();

        registerEvent(new DeviceSuspendedEvent(this.id, reason));
    }

    public void updateToken(DeviceToken newToken) {
        if (newToken == null) {
            throw new IllegalArgumentException("Token cannot be null");
        }
        this.token = newToken;
        this.updatedAt = Instant.now();

        registerEvent(new DeviceTokenUpdatedEvent(this.id, newToken));
    }

    public void updateMetadata(DeviceMetadata newMetadata) {
        if (newMetadata == null) {
            throw new IllegalArgumentException("Metadata cannot be null");
        }
        this.metadata = newMetadata;
        this.updatedAt = Instant.now();
    }

    public void updateLastActive() {
        this.lastActiveAt = Instant.now();
        this.updatedAt = Instant.now();
    }

    public boolean isActive() {
        return this.status == DeviceStatus.ACTIVE;
    }

    public UserId getUserId() {
        return userId;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public DeviceStatus getStatus() {
        return status;
    }

    public DeviceToken getToken() {
        return token;
    }

    public DeviceMetadata getMetadata() {
        return metadata;
    }

    public Instant getLastActiveAt() {
        return lastActiveAt;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}

