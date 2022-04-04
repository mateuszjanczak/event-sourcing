package com.mateuszjanczak.eventsourcing.domain;

import com.mateuszjanczak.eventsourcing.event.Event;
import com.mateuszjanczak.eventsourcing.event.UserCreatedEvent;
import com.mateuszjanczak.eventsourcing.event.UserDisabledEvent;
import com.mateuszjanczak.eventsourcing.event.UserUpdatedEvent;
import com.mateuszjanczak.eventsourcing.store.UserEventStore;

import java.util.List;
import java.util.Optional;

public class User {
    private String userId;
    private String firstName;
    private String lastName;
    private boolean isActive;

    public void createUser(String userId, String firstName, String lastName) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isActive = true;
    }

    public String getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public boolean isActive() {
        return isActive;
    }

    public void enableUser() {
        this.isActive = true;
    }

    public void disableUser() {
        this.isActive = false;
    }

    public void updateUser(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public static Optional<User> recreateUserState(String userId, UserEventStore userEventStore) {
        User user = null;
        List<Event> events = userEventStore.getEvents(userId);

        for (Event event : events) {
            if (event instanceof UserCreatedEvent) {
                UserCreatedEvent e = (UserCreatedEvent) event;
                user = new User();
                user.createUser(e.getUserId(), e.getFirstName(), e.getLastName());
            }

            if (event instanceof UserDisabledEvent && user != null) {
                user.enableUser();
            }

            if (event instanceof UserDisabledEvent && user != null) {
                user.disableUser();
            }

            if (event instanceof UserUpdatedEvent && user != null) {
                UserUpdatedEvent e = (UserUpdatedEvent) event;
                user.updateUser(e.getFirstName(), e.getLastName());
            }
        }

        return Optional.ofNullable(user);
    }
}
