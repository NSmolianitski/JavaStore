package co.baboon.store.users.controllers;

import co.baboon.store.users.handlers.GetAllUsersHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/v1")
public class UserController {
    private final GetAllUsersHandler getAllUsersHandler;

    public UserController(GetAllUsersHandler getAllUsersHandler) {
        this.getAllUsersHandler = getAllUsersHandler;
    }

    @GetMapping
    public ResponseEntity<?> getUsers() {
        return getAllUsersHandler.handle();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        return ResponseEntity.ok("Got user from db with id: " + id);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody Long id) {
        return ResponseEntity.ok("Created user with id: " + id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        return ResponseEntity.ok("Deleted user with id: " + id);
    }
}
