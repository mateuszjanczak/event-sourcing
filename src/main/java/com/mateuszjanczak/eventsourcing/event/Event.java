package com.mateuszjanczak.eventsourcing.event;

import java.util.Date;
import java.util.UUID;

public abstract class Event {
    private final String id;
    private final String userId;
    private final String type;
    private final Date date;

    public Event(String userId) {
        this.id = UUID.randomUUID().toString();
        this.userId = userId;
        this.type = getClass().getSimpleName();
        this.date = new Date();
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getType() {
        return type;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Event { id: " + id + ", userId: " + userId + ", type: " + type + ", date: " + date +" } ";
    }
}
