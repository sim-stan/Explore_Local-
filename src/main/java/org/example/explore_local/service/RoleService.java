package org.example.explore_local.service;

import org.example.explore_local.model.entity.Role;
import org.example.explore_local.model.enums.RoleName;
import org.example.explore_local.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    public Role findByName(RoleName name) {
        return roleRepository.findByName(name).orElse(null);
    }


    public void saveAll(List<Role> roles) {
        roleRepository.saveAll(roles);
    }


    public List<Role> findAll() {
        return roleRepository.findAll();
    }
}
