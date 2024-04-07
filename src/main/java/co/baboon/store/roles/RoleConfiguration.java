package co.baboon.store.roles;

import co.baboon.store.roles.repositories.RoleRepository;
import co.baboon.store.roles.repositories.RoleRepositoryImpl;
import co.baboon.store.roles.services.RoleService;
import co.baboon.store.roles.services.RoleServiceImpl;
import org.jooq.DSLContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoleConfiguration {
    @Bean
    public RoleRepository roleRepository(DSLContext context) {
        return new RoleRepositoryImpl(context);
    }
    
    @Bean
    public RoleService roleService(RoleRepository roleRepository) {
        return new RoleServiceImpl(roleRepository);
    }
}
