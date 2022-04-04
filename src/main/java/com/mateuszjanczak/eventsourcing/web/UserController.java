package com.mateuszjanczak.eventsourcing.web;

import com.mateuszjanczak.eventsourcing.command.CreateUserCommand;
import com.mateuszjanczak.eventsourcing.command.DisableUserCommand;
import com.mateuszjanczak.eventsourcing.command.EnableUserCommand;
import com.mateuszjanczak.eventsourcing.command.UpdateUserCommand;
import com.mateuszjanczak.eventsourcing.handler.UserCommandHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserCommandHandler commandHandler;

    public UserController(UserCommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    @PostMapping("/create-user")
    public void createUser(@RequestBody CreateUserCommand command) {
        commandHandler.handleCreateUser(command);
    }

    @PostMapping("/update-user")
    public void updateUser(@RequestBody UpdateUserCommand command) {
        commandHandler.handleUpdateUser(command);
    }

    @PostMapping("/enable-user")
    public void enableUser(@RequestBody EnableUserCommand command) {
        commandHandler.handleEnableUser(command);
    }

    @PostMapping("/disable-user")
    public void disableUser(@RequestBody DisableUserCommand command) {
        commandHandler.handleDisableUser(command);
    }
}
