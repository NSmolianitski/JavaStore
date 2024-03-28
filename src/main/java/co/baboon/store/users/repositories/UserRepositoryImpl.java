package co.baboon.store.users.repositories;

import co.baboon.store.users.User;
import org.jooq.DSLContext;

import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    private final DSLContext context;

    public UserRepositoryImpl(DSLContext context) {
        this.context = context;
    }

    @Override
    public void addUser() {
        
    }

    @Override
    public User getUser() {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public void updateUser() {

    }

    @Override
    public void deleteUser() {

    }
}
