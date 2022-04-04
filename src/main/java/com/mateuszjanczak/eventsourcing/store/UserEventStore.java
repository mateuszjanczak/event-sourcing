package com.mateuszjanczak.eventsourcing.store;

import com.mateuszjanczak.eventsourcing.event.Event;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class UserEventStore {

    private final Map<String, Event> store = new HashMap<>();

    public void addEvent(Event event) {
        store.put(event.getId(), event);
    }

    public List<Event> getEvents(String userId) {
        return new ArrayList<>(store.values()).stream().filter(event -> event.getUserId().equals(userId)).collect(Collectors.toList());
    }
}