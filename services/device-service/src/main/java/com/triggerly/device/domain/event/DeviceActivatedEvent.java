package com.triggerly.device.domain.event;

import com.triggerly.device.domain.model.valueobjects.DeviceId;
import triggerly.common.event.domain.BaseDomainEvent;

public class DeviceActivatedEvent extends BaseDomainEvent {

    private final DeviceId deviceId;

    public DeviceActivatedEvent(DeviceId deviceId) {
        this.deviceId = deviceId;
    }

    public DeviceId getDeviceId() {
        return deviceId;
    }
}

