package com.triggerly.auth.domain.event;

import com.triggerly.auth.domain.model.User;
import triggerly.common.event.domain.BaseDomainEvent;

import java.time.Instant;

public class UserLoggedInEvent extends BaseDomainEvent {

    private final User user;
    private final Instant loginAt;
    private final String ipAddress;
    private final String userAgent;

    public UserLoggedInEvent(User user, Instant loginAt, String ipAddress, String userAgent) {
        this.user = user;
        this.loginAt = loginAt;
        this.ipAddress = ipAddress;
        this.userAgent = userAgent;
    }

    public User getUser() {
        return user;
    }

    public Instant getLoginAt() {
        return loginAt;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public String getUserAgent() {
        return userAgent;
    }
}

