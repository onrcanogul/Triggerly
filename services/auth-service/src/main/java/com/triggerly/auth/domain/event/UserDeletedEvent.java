package com.triggerly.auth.domain.event;

import com.triggerly.auth.domain.model.User;
import triggerly.common.event.domain.BaseDomainEvent;

public class UserDeletedEvent extends BaseDomainEvent {

    private final User user;

    public UserDeletedEvent(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}

