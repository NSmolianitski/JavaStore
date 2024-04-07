package co.baboon.store.roles.services;

import co.baboon.store.roles.Role;

public interface RoleService {
    Role getRoleById(Long id);
    Role getRoleByName(String name);
}
