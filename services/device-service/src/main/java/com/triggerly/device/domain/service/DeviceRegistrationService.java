package com.triggerly.device.domain.service;

import com.triggerly.device.domain.exception.DeviceAlreadyExistsException;
import com.triggerly.device.domain.model.Device;
import com.triggerly.device.domain.model.valueobjects.*;

import java.util.List;

public class DeviceRegistrationService {

    private final DeviceRepository deviceRepository;

    public DeviceRegistrationService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public Device registerNewDevice(
            DeviceId deviceId,
            UserId userId,
            DeviceType deviceType,
            DeviceToken token,
            DeviceMetadata metadata
    ) {
        if (deviceRepository.existsById(deviceId)) {
            throw new DeviceAlreadyExistsException("Device with id " + deviceId.value() + " already exists");
        }

        Device device = Device.register(deviceId, userId, deviceType, token, metadata);
        return deviceRepository.save(device);
    }

    public List<Device> getUserDevices(UserId userId) {
        return deviceRepository.findByUserId(userId);
    }

    public long countActiveDevicesForUser(UserId userId) {
        return deviceRepository.findByUserId(userId).stream()
                .filter(Device::isActive)
                .count();
    }
}

