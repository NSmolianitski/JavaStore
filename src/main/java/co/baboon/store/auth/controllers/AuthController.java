package co.baboon.store.auth.controllers;

import co.baboon.store.auth.dto.LoginDto;
import co.baboon.store.auth.dto.RegisterDto;
import co.baboon.store.auth.handlers.LoginHandler;
import co.baboon.store.auth.handlers.RegisterHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RestController
@RequestMapping("/auth/v1/")
public class AuthController implements WebMvcConfigurer {
    private final RegisterHandler registerHandler;
    private final LoginHandler loginHandler;
    
    public AuthController(RegisterHandler registerHandler, LoginHandler loginHandler) {
        this.registerHandler = registerHandler;
        this.loginHandler = loginHandler;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDto registerDto) {
        return registerHandler.handle(registerDto);
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        return loginHandler.handle(loginDto);
    }
}
