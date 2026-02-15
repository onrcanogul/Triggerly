package com.triggerly.auth.domain.model;

import com.triggerly.auth.domain.event.UserDeletedEvent;
import com.triggerly.auth.domain.event.UserRegisteredEvent;
import com.triggerly.auth.domain.model.valueobject.Email;
import com.triggerly.auth.domain.model.valueobject.Password;
import com.triggerly.auth.domain.model.valueobject.UserId;
import com.triggerly.auth.domain.model.valueobject.Username;
import triggerly.common.ddd.AggregateRoot;

import java.time.Instant;

public class User extends AggregateRoot<UserId> {

    private Username username;
    private Email email;
    private Password password;
    private Instant createdAt;
    private Instant updatedAt;
    private boolean deleted;

    private User(UserId id, Username username, Email email, Password password, Instant createdAt, Instant updatedAt, boolean deleted) {
        super(id);
        this.username = username;
        this.email = email;
        this.password = password;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deleted = deleted;
    }

    public static User create(Username username, Email email, Password hashedPassword) {
        Instant now = Instant.now();
        User user = new User(
                UserId.generate(),
                username,
                email,
                hashedPassword,
                now,
                now,
                false
        );
        user.registerEvent(new UserRegisteredEvent(user));
        return user;
    }

    public static User reconstitute(UserId id, Username username, Email email, Password password,
                                   Instant createdAt, Instant updatedAt, boolean deleted) {
        return new User(id, username, email, password, createdAt, updatedAt, deleted);
    }

    public void updatePassword(Password newHashedPassword) {
        this.password = newHashedPassword;
        this.updatedAt = Instant.now();
    }

    public void updateUsername(Username newUsername) {
        this.username = newUsername;
        this.updatedAt = Instant.now();
    }

    public void markAsDeleted() {
        this.deleted = true;
        this.updatedAt = Instant.now();
        registerEvent(new UserDeletedEvent(this));
    }

    public boolean verifyPassword(String rawPassword, PasswordEncoder encoder) {
        return encoder.matches(rawPassword, this.password.value());
    }

    public Username getUsername() {
        return username;
    }

    public Email getEmail() {
        return email;
    }

    public Password getPassword() {
        return password;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public interface PasswordEncoder {
        boolean matches(String rawPassword, String encodedPassword);
    }
}

