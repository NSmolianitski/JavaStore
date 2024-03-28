package co.baboon.store.users.controllers.handlers;

import co.baboon.store.users.repositories.UserService;
import org.springframework.http.ResponseEntity;

public class GetAllUsersHandler {
    private final UserService userService;

    public GetAllUsersHandler(UserService userService) {
        this.userService = userService;
    }

    public ResponseEntity<?> handle() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
}
