package co.baboon.store.users;

import co.baboon.store.users.controllers.handlers.GetAllUsersHandler;
import co.baboon.store.users.repositories.UserRepository;
import co.baboon.store.users.repositories.UserRepositoryImpl;
import co.baboon.store.users.repositories.UserService;
import org.jooq.DSLContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfiguration {
    @Bean
    public GetAllUsersHandler getAllUsersHandler(UserService userService) { return new GetAllUsersHandler(userService); }
    
    @Bean
    public UserRepository userRepository(DSLContext context) { return new UserRepositoryImpl(context); }
    
    @Bean
    public UserService userService(UserRepository userRepository) { return new UserService(userRepository); }
}
