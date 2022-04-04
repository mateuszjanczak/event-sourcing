package com.mateuszjanczak.eventsourcing.command;

public class EnableUserCommand {
    private final String userId;

    public EnableUserCommand(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }
}
