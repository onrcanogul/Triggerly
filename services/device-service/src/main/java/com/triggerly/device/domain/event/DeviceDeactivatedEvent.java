package com.triggerly.device.domain.event;

import com.triggerly.device.domain.model.valueobjects.DeviceId;
import triggerly.common.event.domain.BaseDomainEvent;

public class DeviceDeactivatedEvent extends BaseDomainEvent {

    private final DeviceId deviceId;

    public DeviceDeactivatedEvent(DeviceId deviceId) {
        this.deviceId = deviceId;
    }

    public DeviceId getDeviceId() {
        return deviceId;
    }
}

