package co.baboon.store.auth.services.implementations;

import co.baboon.store.auth.dto.JwtResponseDto;
import co.baboon.store.auth.dto.LoginDto;
import co.baboon.store.auth.services.JwtService;
import co.baboon.store.auth.services.LoginService;
import co.baboon.store.users.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class LoginServiceImpl implements LoginService {
    private final UserService userService;
    private final JwtService jwtService;

    public LoginServiceImpl(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @Override
    public ResponseEntity<?> login(LoginDto loginDto) {
        var userOptional = userService.getUserByUsername(loginDto.username());
        if (userOptional.isEmpty())
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        
        var user = userOptional.get();
        
        var jwt = jwtService.generateToken(user);
        return ResponseEntity.ok().body(new JwtResponseDto(jwt));
    }
}
