package com.triggerly.device.domain.event;

import com.triggerly.device.domain.model.valueobjects.DeviceId;
import com.triggerly.device.domain.model.valueobjects.DeviceToken;
import triggerly.common.event.domain.BaseDomainEvent;

public class DeviceTokenUpdatedEvent extends BaseDomainEvent {

    private final DeviceId deviceId;
    private final DeviceToken newToken;

    public DeviceTokenUpdatedEvent(DeviceId deviceId, DeviceToken newToken) {
        this.deviceId = deviceId;
        this.newToken = newToken;
    }

    public DeviceId getDeviceId() {
        return deviceId;
    }

    public DeviceToken getNewToken() {
        return newToken;
    }
}

