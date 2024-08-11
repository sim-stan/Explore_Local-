package org.example.explore_local.service;

import org.example.explore_local.model.dtos.CityDTO;
import org.example.explore_local.model.entity.City;
import org.example.explore_local.model.view.CityViewModel;
import org.example.explore_local.repository.CityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
        return
                new CityViewModel(
                        city.getId(),
                        city.getName(),
                        city.getAbout(),
                        city.getBusinesses().size());

    }

    public CityViewModel getCityViewModelById(long id) {

        Optional<City> city = cityRepository.findById(id);

        if (city.isPresent()) {
            return modelMapper.map(city, CityViewModel.class);
        } else {
            throw new IllegalArgumentException("City not exist!");
        }

    }

    public City getCityById(long id) {

        Optional<City> city = cityRepository.findById(id);
        if (city.isPresent()) {
            return modelMapper.map(city, City.class);
        } else {
            throw new IllegalArgumentException("City not exist!");
        }

    }

    @Transactional
    public void seedCities() {

        if (cityRepository.count() <= 0) {


            City city1 = new City();
            city1.setName("Sofia");
            city1.setAbout("Sofia, the capital of Bulgaria, is a city where ancient history meets" +
                    " modern vibrancy. ");
            cityRepository.save(city1);

            City city2 = new City();
            city2.setName("New York");
            city2.setAbout("New York City, the city that never sleeps, is a" +
                    " bustling metropolis known for its towering skyscrapers," +
                    " world-famous landmarks, and vibrant cultural scene. ");
            cityRepository.save(city2);


            City city3 = new City();
            city3.setName("London");
            city3.setAbout("London is a historic yet modern city, known for its iconic landmarks " +
                    "like Big Ben and the Tower of London. ");
            cityRepository.save(city3);

            City city4 = new City();
            city4.setName("Miami");
            city4.setAbout("Miami is a vibrant city known for its stunning beaches, " +
                    "lively nightlife, and rich cultural diversity.");
            cityRepository.save(city4);

            City city5 = new City();
            city5.setName("Los Angeles");
            city5.setAbout("Los Angeles, the City of Angels, is a sprawling metropolis known" +
                    " for its sunny weather, diverse culture, and entertainment industry. ");
            cityRepository.save(city5);
        }
    }

}
