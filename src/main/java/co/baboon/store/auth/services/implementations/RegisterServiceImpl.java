package co.baboon.store.auth.services.implementations;

import co.baboon.store.auth.dto.JwtResponseDto;
import co.baboon.store.auth.dto.RegisterDto;
import co.baboon.store.auth.services.JwtService;
import co.baboon.store.auth.services.RegisterService;
import co.baboon.store.roles.Role;
import co.baboon.store.roles.RoleTypes;
import co.baboon.store.roles.services.RoleService;
import co.baboon.store.users.User;
import co.baboon.store.users.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RegisterServiceImpl implements RegisterService {
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserService userService;
    private final RoleService roleService;

    public RegisterServiceImpl(PasswordEncoder passwordEncoder,
                               JwtService jwtService,
                               UserService userService, RoleService roleService) {
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.userService = userService;
        this.roleService = roleService;
    }
    
    public ResponseEntity<?> register(RegisterDto registerDto) {
        var username = registerDto.username();
        if (!usernameIsFree(username))
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username \"" + username + "\" already taken.");
        
        var hashedPassword = passwordEncoder.encode(registerDto.password());
        var defaultUserRole = roleService.getRoleByName("ROLE_USER");
        var userWithoutId = User.builder()
                .withUsername(registerDto.username())
                .withPassword(hashedPassword)
                .withRoles(Set.of(defaultUserRole))
                .build();

        var userFromDb = userService.addUser(userWithoutId);
        
        var jwt = jwtService.generateToken(userFromDb);

        return ResponseEntity.status(HttpStatus.CREATED).body(new JwtResponseDto(jwt));
    }

    private Boolean usernameIsFree(String username) {
        return userService.getUserByUsername(username).isEmpty();
    }
}
