package co.baboon.store.users.repositories;

import co.baboon.store.users.User;
import org.springframework.stereotype.Service;

import java.util.List;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public void addUser() {
        userRepository.addUser();
    }
    
    public User getUser() {
        return userRepository.getUser();
    }
    
    public void updateUser() {
        userRepository.updateUser();
    }
    
    public void deleteUser() {
        userRepository.deleteUser();
    }

    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }
}
