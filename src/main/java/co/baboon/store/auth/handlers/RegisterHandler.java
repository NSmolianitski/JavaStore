package co.baboon.store.auth.handlers;

import co.baboon.store.auth.dto.RegisterDto;
import co.baboon.store.auth.services.RegisterService;
import org.springframework.http.ResponseEntity;

public class RegisterHandler {
    private final RegisterService registerService;

    public RegisterHandler(RegisterService registerService) {
        this.registerService = registerService;
    }

    public ResponseEntity<?> handle(RegisterDto registerDto) {
        return registerService.register(registerDto);
    }
}
