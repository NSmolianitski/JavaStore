package co.baboon.store.auth.services;

import co.baboon.store.auth.dto.RegisterDto;
import org.springframework.http.ResponseEntity;

public interface RegisterService {
    ResponseEntity<?> register(RegisterDto registerDto);
}
