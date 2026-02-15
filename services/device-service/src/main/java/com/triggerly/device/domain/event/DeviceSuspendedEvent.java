package com.triggerly.device.domain.event;

import com.triggerly.device.domain.model.valueobjects.DeviceId;
import triggerly.common.event.domain.BaseDomainEvent;

public class DeviceSuspendedEvent extends BaseDomainEvent {

    private final DeviceId deviceId;
    private final String reason;

    public DeviceSuspendedEvent(DeviceId deviceId, String reason) {
        this.deviceId = deviceId;
        this.reason = reason;
    }

    public DeviceId getDeviceId() {
        return deviceId;
    }

    public String getReason() {
        return reason;
    }
}

