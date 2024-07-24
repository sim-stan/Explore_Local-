package org.example.explore_local.service;

import org.example.explore_local.model.entity.City;
import org.example.explore_local.model.view.CityViewModel;
import org.springframework.stereotype.Service;

@Service
public class CitiesService {


    private CityViewModel cityViewModel(City city) {
        return new CityViewModel();
    }
}
