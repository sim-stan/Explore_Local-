package org.example.explore_local.repository;

import org.example.explore_local.model.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City,Long> {

    City getCityByName(String name);


}
