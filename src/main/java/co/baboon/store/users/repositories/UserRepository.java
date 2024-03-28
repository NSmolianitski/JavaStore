package co.baboon.store.users.repositories;

import co.baboon.store.users.User;

import java.util.List;

public interface UserRepository {
    void addUser();
    User getUser();
    List<User> getAllUsers();
    void updateUser();
    void deleteUser();
}
