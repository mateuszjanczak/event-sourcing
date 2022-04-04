package com.mateuszjanczak.eventsourcing.event;

public class UserDisabledEvent extends Event {

    public UserDisabledEvent(String userId) {
        super(userId);
    }

}
