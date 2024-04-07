package co.baboon.store.users.handlers;

import co.baboon.store.users.User;
import co.baboon.store.users.services.UserService;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class GetAllUsersHandler {
    private final UserService userService;

    public GetAllUsersHandler(UserService userService) {
        this.userService = userService;
    }

    public ResponseEntity<?> handle() {
        var usersList = userService.getAllUsers();
        return ResponseEntity.ok(List.of(usersList));
    }
}
