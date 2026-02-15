package com.triggerly.device.domain.service;

import com.triggerly.device.domain.model.Device;
import com.triggerly.device.domain.model.valueobjects.DeviceId;
import com.triggerly.device.domain.model.valueobjects.UserId;

import java.util.List;

public interface DeviceRepository {

    Device save(Device device);

    Device findById(DeviceId deviceId);

    List<Device> findByUserId(UserId userId);

    boolean existsById(DeviceId deviceId);

    void deleteById(DeviceId deviceId);
}

