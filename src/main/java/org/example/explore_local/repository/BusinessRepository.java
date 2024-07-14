package org.example.explore_local.repository;

import org.example.explore_local.model.entity.Business;
import org.example.explore_local.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BusinessRepository extends JpaRepository<Business,Long> {
    Optional<Business> findByName(String name);

    Optional<Business> findByEmail(String email);

}
