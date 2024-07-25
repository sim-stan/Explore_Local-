package org.example.explore_local.service;

import org.example.explore_local.model.dtos.CityDTO;
import org.example.explore_local.model.entity.City;
import org.example.explore_local.model.view.CityViewModel;
import org.example.explore_local.repository.CityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CitiesService {


    private final CityRepository cityRepository;
    private final ModelMapper modelMapper;





    public void addCity(CityDTO cityDTO) {

        City city = this.modelMapper.map(cityDTO, City.class);
       cityRepository.save(city);
    }

    public CitiesService(CityRepository cityRepository, ModelMapper modelMapper) {
        this.cityRepository = cityRepository;
        this.modelMapper = modelMapper;
    }

    public List<CityViewModel> getAllCitiesViewModels() {
        return cityRepository.findAll()
                .stream()
                .map(CitiesService::mapToCityViewModel)
                .toList();
    }


    private static CityViewModel mapToCityViewModel(City city) {
        return new CityViewModel(city.getName(),
                city.getAbout(),
                city.getBusinesses().size());

    }
}
