package co.baboon.store.roles.repositories;

import co.baboon.store.roles.Role;

import java.util.Optional;

public interface RoleRepository {
    Optional<Role> getRoleById(Long id);
    Optional<Role> getRoleByName(String name);
}
