package org.example.explore_local.service;

import org.example.explore_local.model.dtos.CityDTO;
import org.example.explore_local.model.entity.City;
import org.example.explore_local.model.view.CityViewModel;
import org.example.explore_local.repository.CityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {


    private final CityRepository cityRepository;
    private final ModelMapper modelMapper;



    public CityService(CityRepository cityRepository, ModelMapper modelMapper) {
        this.cityRepository = cityRepository;
        this.modelMapper = modelMapper;
    }


    public void addCity(CityDTO cityDTO) {

        City city = this.modelMapper.map(cityDTO, City.class);
       cityRepository.save(city);
    }


    public List<CityViewModel> getAllCitiesViewModels() {
        return cityRepository.findAll()
                .stream()
                .map(CityService::mapToCityViewModel)
                .toList();
    }


    private static CityViewModel mapToCityViewModel(City city) {
        return new CityViewModel(city.getName(),
                city.getAbout(),
                city.getBusinesses().size());

    }

//    public City getCityByName(String name){
//        return  cityRepository.getCityByName(name);
//    }

    public void seedCities() {

        if (cityRepository.count() <= 0) {

            City city = new City();
            city.setName("Sofia");
            city.setAbout("Sofia is the capital of Bulgaria");
            cityRepository.save(city);

            City city2 = new City();
            city2.setName("New York");
            city2.setAbout("New York is one of the most popular travel destinations in the world");
            cityRepository.save(city2);


            City city3 = new City();
            city3.setName("London");
            city3.setAbout("London, the capital of England and the United Kingdom");
            cityRepository.save(city3);
        }
    }

}
