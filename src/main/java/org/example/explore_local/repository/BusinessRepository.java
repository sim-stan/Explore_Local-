package org.example.explore_local.repository;

import org.example.explore_local.model.entity.Business;
import org.example.explore_local.model.entity.Category;
import org.example.explore_local.model.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BusinessRepository extends JpaRepository<Business,Long> {
    Optional<Business> findByName(String name);

    Optional<Business> findByEmail(String email);

    List<Business> getBusinessesByCategory(Category categoryName);
    List<Business> getBusinessesByCity(City city);

    List<Business> getBusinessesByCity(Optional<City> city);

    List<Business> getAllBusinessesByCategory(Category name);

}
