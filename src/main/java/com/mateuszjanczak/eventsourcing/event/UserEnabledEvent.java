package com.mateuszjanczak.eventsourcing.event;

public class UserEnabledEvent extends Event {

    public UserEnabledEvent(String userId) {
        super(userId);
    }

}
