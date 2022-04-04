package com.mateuszjanczak.eventsourcing.command;

public class DisableUserCommand {
    private final String userId;

    public DisableUserCommand(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }
}
