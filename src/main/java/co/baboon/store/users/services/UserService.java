package co.baboon.store.users.services;

import co.baboon.store.users.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    User addUser(User user);
    Optional<User> getUserById(Long id);
    Optional<User> getUserByUsername(String login);
    List<User> getAllUsers();
    void updateUser(User user);
    void deleteUserById(Long id);
}
