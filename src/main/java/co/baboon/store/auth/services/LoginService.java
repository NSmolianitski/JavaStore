package co.baboon.store.auth.services;

import co.baboon.store.auth.dto.LoginDto;
import org.springframework.http.ResponseEntity;

public interface LoginService {
    ResponseEntity<?> login(LoginDto loginDto);
}
