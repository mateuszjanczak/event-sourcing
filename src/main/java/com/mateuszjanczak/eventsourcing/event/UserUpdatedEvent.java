package com.mateuszjanczak.eventsourcing.event;

public class UserUpdatedEvent extends Event {
    private final String firstName;
    private final String lastName;

    public UserUpdatedEvent(String userId, String firstName, String lastName) {
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
