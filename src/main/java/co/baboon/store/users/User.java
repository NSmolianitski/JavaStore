package co.baboon.store.users;

public record User(
        Long id,
        String login,
        String password
) {}
