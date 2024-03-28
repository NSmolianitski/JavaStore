package co.baboon.store.users.controllers;

import co.baboon.store.users.controllers.handlers.GetAllUsersHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("users")
public class UserController {
    private final GetAllUsersHandler getAllUsersHandler;

    public UserController(GetAllUsersHandler getAllUsersHandler) {
        this.getAllUsersHandler = getAllUsersHandler;
    }

    @GetMapping
    public ResponseEntity<?> getUsers() {
        return getAllUsersHandler.handle();
    }
}
