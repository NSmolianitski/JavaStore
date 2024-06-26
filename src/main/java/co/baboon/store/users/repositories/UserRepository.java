package co.baboon.store.users.repositories;

import co.baboon.store.users.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User addUser(User user);
    Optional<User> getUserById(Long id);
    Optional<User> getUserByLogin(String login);
    List<User> getAllUsers();
    void updateUser(User user);
    void deleteUserById(Long id);
}
