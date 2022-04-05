package com.mateuszjanczak.eventsourcing.handler;

import com.mateuszjanczak.eventsourcing.aggregate.UserAggregate;
import com.mateuszjanczak.eventsourcing.command.CreateUserCommand;
import com.mateuszjanczak.eventsourcing.command.DisableUserCommand;
import com.mateuszjanczak.eventsourcing.command.EnableUserCommand;
import com.mateuszjanczak.eventsourcing.command.UpdateUserCommand;
import com.mateuszjanczak.eventsourcing.event.UserCreatedEvent;
import com.mateuszjanczak.eventsourcing.event.UserDisabledEvent;
import com.mateuszjanczak.eventsourcing.event.UserEnabledEvent;
import com.mateuszjanczak.eventsourcing.event.UserUpdatedEvent;
import com.mateuszjanczak.eventsourcing.store.UserEventStore;
import org.springframework.stereotype.Service;

@Service
public class UserCommandHandler {

    private final UserEventStore userEventStore;

    public UserCommandHandler(UserEventStore userEventStore) {
        this.userEventStore = userEventStore;
    }

    public void handleCreateUser(CreateUserCommand command) {
        UserAggregate userAggregate = new UserAggregate();
        UserCreatedEvent userCreatedEvent = new UserCreatedEvent(command.getUserId(), command.getFirstName(), command.getLastName());
        userAggregate.apply(userCreatedEvent);
        userEventStore.addEvent(userCreatedEvent);
    }

    public void handleEnableUser(EnableUserCommand command) {
        UserAggregate userAggregate = getUser(command.getUserId());
        UserEnabledEvent userEnabledEvent = new UserEnabledEvent(command.getUserId());
        userAggregate.apply(userEnabledEvent);
        userEventStore.addEvent(userEnabledEvent);
    }

    public void handleDisableUser(DisableUserCommand command) {
        UserAggregate userAggregate = getUser(command.getUserId());
        UserDisabledEvent userDisabledEvent = new UserDisabledEvent(command.getUserId());
        userAggregate.apply(userDisabledEvent);
        userEventStore.addEvent(userDisabledEvent);
    }

    public void handleUpdateUser(UpdateUserCommand command) {
        UserAggregate userAggregate = getUser(command.getUserId());
        UserUpdatedEvent userUpdatedEvent = new UserUpdatedEvent(command.getUserId(), command.getFirstName(), command.getLastName());
        userAggregate.apply(userUpdatedEvent);
        userEventStore.addEvent(userUpdatedEvent);
    }

    private UserAggregate getUser(String userId) {
        UserAggregate userAggregate = new UserAggregate();
        userAggregate.recreateUserState(userEventStore.getEvents(userId));
        return userAggregate;
    }
}
