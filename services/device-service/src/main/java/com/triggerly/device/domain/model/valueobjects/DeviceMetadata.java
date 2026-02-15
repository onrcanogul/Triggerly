package com.triggerly.device.domain.model.valueobjects;

import java.util.Objects;

public final class DeviceMetadata {

    private final String osVersion;
    private final String appVersion;
    private final String deviceModel;

    public DeviceMetadata(String osVersion, String appVersion, String deviceModel) {
        this.osVersion = osVersion;
        this.appVersion = appVersion;
        this.deviceModel = deviceModel;
    }

    public String osVersion() {
        return osVersion;
    }

    public String appVersion() {
        return appVersion;
    }

    public String deviceModel() {
        return deviceModel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DeviceMetadata that)) return false;
        return Objects.equals(osVersion, that.osVersion) &&
                Objects.equals(appVersion, that.appVersion) &&
                Objects.equals(deviceModel, that.deviceModel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(osVersion, appVersion, deviceModel);
    }
}

