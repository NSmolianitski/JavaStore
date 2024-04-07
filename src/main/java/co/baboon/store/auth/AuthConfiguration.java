package co.baboon.store.auth;

import co.baboon.store.auth.handlers.LoginHandler;
import co.baboon.store.auth.handlers.RegisterHandler;
import co.baboon.store.auth.services.JwtService;
import co.baboon.store.auth.services.LoginService;
import co.baboon.store.auth.services.implementations.JwtServiceImpl;
import co.baboon.store.auth.services.RegisterService;
import co.baboon.store.auth.services.implementations.LoginServiceImpl;
import co.baboon.store.auth.services.implementations.RegisterServiceImpl;
import co.baboon.store.roles.RoleTypes;
import co.baboon.store.roles.services.RoleService;
import co.baboon.store.users.services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
public class AuthConfiguration {
    @Bean
    public JwtAuthFilter jwtAuthFilter(JwtService jwtService, UserService userService) {
        return new JwtAuthFilter(jwtService, userService);
    }
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthFilter jwtAuthFilter) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .sessionManagement(manager -> manager.sessionCreationPolicy(STATELESS))
                .exceptionHandling(handler -> handler.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/auth/v1/login").permitAll()
                        .requestMatchers("/auth/v1/register").permitAll()
                        
                        .requestMatchers("/users/v1").hasRole("USER")
                        
                        .anyRequest().permitAll())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public JwtService jwtService(AuthProps authProps) {
        return new JwtServiceImpl(authProps);
    }
    
    @Bean
    public RegisterService registerService(PasswordEncoder passwordEncoder,
                                           JwtService jwtService,
                                           UserService userService,
                                           RoleService roleService) {
        return new RegisterServiceImpl(passwordEncoder, jwtService, userService, roleService);
    }
    
    @Bean
    public LoginService loginService(UserService userService,
                                     JwtService jwtService) {
        return new LoginServiceImpl(userService, jwtService);
    }
    
    @Bean
    public RegisterHandler registerHandler(RegisterService registerService) {
        return new RegisterHandler(registerService);
    }
    
    @Bean
    public LoginHandler loginHandler(LoginService loginService) {
        return new LoginHandler(loginService);
    }
}
