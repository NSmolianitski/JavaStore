package co.baboon.store.auth.services;

import co.baboon.store.users.User;

public interface JwtService {
    String generateToken(User user);
    Long extractUserId(String token);
}
