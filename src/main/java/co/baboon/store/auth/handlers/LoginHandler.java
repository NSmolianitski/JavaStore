package co.baboon.store.auth.handlers;

import co.baboon.store.auth.dto.LoginDto;
import co.baboon.store.auth.services.LoginService;
import co.baboon.store.users.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

public class LoginHandler {
    private final LoginService loginService;

    public LoginHandler(LoginService loginService) {
        this.loginService = loginService;
    }
    
    public ResponseEntity<?> handle(LoginDto loginDto) {
        return loginService.login(loginDto);
    }
}
