package com.mateuszjanczak.eventsourcing.event;

import com.mateuszjanczak.eventsourcing.type.UserEventType;

import java.util.Date;
import java.util.UUID;

public abstract class Event {
    private final String id;
    private final String userId;
    private final UserEventType userEventType;
    private final Date date;

    public Event(String userId) {
        this.id = UUID.randomUUID().toString();
        this.userId = userId;
        this.userEventType = UserEventType.valueOf(getClass().getSimpleName());
        this.date = new Date();
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public UserEventType getEventType() {
        return userEventType;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Event { id: " + id + ", userId: " + userId + ", eventType: " + userEventType + ", date: " + date +" } ";
    }
}
