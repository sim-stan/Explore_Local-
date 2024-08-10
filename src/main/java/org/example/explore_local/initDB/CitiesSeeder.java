package org.example.explore_local.initDB;

import org.example.explore_local.service.CityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CitiesSeeder implements CommandLineRunner {

    private final CityService cityService;


    public CitiesSeeder(CityService cityService) {
        this.cityService = cityService;
    }


    @Override
    public void run(String... args) throws Exception {
        cityService.seedCities();

    }
}
