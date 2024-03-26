package ru.baboonandco.store.users;

public record User(
        Long id,
        String login,
        String password
) {}
