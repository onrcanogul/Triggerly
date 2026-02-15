package com.triggerly.device.domain.event;

import com.triggerly.device.domain.model.valueobjects.DeviceId;
import com.triggerly.device.domain.model.valueobjects.DeviceType;
import com.triggerly.device.domain.model.valueobjects.UserId;
import triggerly.common.event.domain.BaseDomainEvent;

public class DeviceRegisteredEvent extends BaseDomainEvent {

    private final DeviceId deviceId;
    private final UserId userId;
    private final DeviceType deviceType;

    public DeviceRegisteredEvent(DeviceId deviceId, UserId userId, DeviceType deviceType) {
        this.deviceId = deviceId;
        this.userId = userId;
        this.deviceType = deviceType;
    }

    public DeviceId getDeviceId() {
        return deviceId;
    }

    public UserId getUserId() {
        return userId;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }
}

