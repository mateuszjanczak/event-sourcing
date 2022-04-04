package com.mateuszjanczak.eventsourcing.command;

public class CreateUserCommand {
    private final String userId;
    private final String firstName;
    private final String lastName;

    public CreateUserCommand(String userId, String firstName, String lastName) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
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
}
