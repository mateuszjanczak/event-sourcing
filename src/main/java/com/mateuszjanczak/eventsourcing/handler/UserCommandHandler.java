package com.mateuszjanczak.eventsourcing.handler;

import com.mateuszjanczak.eventsourcing.command.CreateUserCommand;
import com.mateuszjanczak.eventsourcing.command.DisableUserCommand;
import com.mateuszjanczak.eventsourcing.command.EnableUserCommand;
import com.mateuszjanczak.eventsourcing.command.UpdateUserCommand;
import com.mateuszjanczak.eventsourcing.domain.User;
import com.mateuszjanczak.eventsourcing.event.UserCreatedEvent;
import com.mateuszjanczak.eventsourcing.event.UserDisabledEvent;
import com.mateuszjanczak.eventsourcing.event.UserEnabledEvent;
import com.mateuszjanczak.eventsourcing.event.UserUpdatedEvent;
import com.mateuszjanczak.eventsourcing.exception.UserNotFoundException;
import com.mateuszjanczak.eventsourcing.store.UserEventStore;
import org.springframework.stereotype.Service;

@Service
public class UserCommandHandler {

    private final UserEventStore userEventStore;

    public UserCommandHandler(UserEventStore userEventStore) {
        this.userEventStore = userEventStore;
    }

    public void handleCreateUser(CreateUserCommand command){
        User user = new User();
        user.createUser(command.getUserId(), command.getFirstName(), command.getLastName());
        userEventStore.addEvent(new UserCreatedEvent(command.getUserId(), command.getFirstName(), command.getLastName()));
    }

    public void handleEnableUser(EnableUserCommand command) {
        User user = getUser(command.getUserId());
        user.enableUser();
        userEventStore.addEvent(new UserEnabledEvent(command.getUserId()));
    }

    public void handleDisableUser(DisableUserCommand command) {
        User user = getUser(command.getUserId());
        user.disableUser();
        userEventStore.addEvent(new UserDisabledEvent(command.getUserId()));
    }

    public void handleUpdateUser(UpdateUserCommand command) {
        User user = getUser(command.getUserId());
        user.updateUser(command.getFirstName(), command.getLastName());
        userEventStore.addEvent(new UserUpdatedEvent(command.getUserId(), command.getFirstName(), command.getLastName()));
    }

    private User getUser(String userId) {
        return User.recreateUserState(userId, userEventStore).orElseThrow(UserNotFoundException::new);
    }
}
