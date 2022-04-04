package com.mateuszjanczak.eventsourcing.event;

public class UserCreatedEvent extends Event {
    private final String firstName;
    private final String lastName;

    public UserCreatedEvent(String userId, String firstName, String lastName) {
        super(userId);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

}
