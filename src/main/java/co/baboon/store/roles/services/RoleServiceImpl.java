package co.baboon.store.roles.services;

import co.baboon.store.roles.Role;
import co.baboon.store.roles.repositories.RoleRepository;

public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getRoleById(Long id) {
        return roleRepository.getRoleById(id).orElseThrow();
    }

    @Override
    public Role getRoleByName(String name) {
        return roleRepository.getRoleByName(name).orElseThrow();
    }
}
