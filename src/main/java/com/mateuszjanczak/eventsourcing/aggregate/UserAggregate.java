package com.mateuszjanczak.eventsourcing.aggregate;

import com.mateuszjanczak.eventsourcing.event.Event;
import com.mateuszjanczak.eventsourcing.event.UserCreatedEvent;
import com.mateuszjanczak.eventsourcing.event.UserDisabledEvent;
import com.mateuszjanczak.eventsourcing.event.UserEnabledEvent;
import com.mateuszjanczak.eventsourcing.event.UserUpdatedEvent;

import java.util.List;

public class UserAggregate {
    private String userId;
    private String firstName;
    private String lastName;
    private boolean active;

    public UserAggregate() {
    }

    UserAggregate(String userId, String firstName, String lastName, boolean active) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.active = active;
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
        return active;
    }

    public void recreateUserState(List<Event> events) {
        for(Event event: events) {
            switch (event.getEventType()) {
                case UserCreatedEvent:
                    apply((UserCreatedEvent) event);
                    break;
                case UserEnabledEvent:
                    apply((UserEnabledEvent) event);
                    break;
                case UserDisabledEvent:
                    apply((UserDisabledEvent) event);
                    break;
                case UserUpdatedEvent:
                    apply((UserUpdatedEvent) event);
                    break;
            }
        }
    }

    public void apply(UserCreatedEvent event) {
        this.userId = event.getUserId();
        this.firstName = event.getFirstName();
        this.lastName = event.getLastName();
        this.active = true;
    }

    public void apply(UserEnabledEvent event) {
        this.active = true;
    }

    public void apply(UserDisabledEvent event) {
        this.active = false;
    }

    public void apply(UserUpdatedEvent event) {
        this.firstName = event.getFirstName();
        this.lastName = event.getLastName();
    }
}
