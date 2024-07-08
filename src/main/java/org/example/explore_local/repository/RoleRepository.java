package org.example.explore_local.repository;

import org.example.explore_local.model.entity.Role;
import org.example.explore_local.model.entity.User;
import org.example.explore_local.model.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(RoleName roleName);

}
