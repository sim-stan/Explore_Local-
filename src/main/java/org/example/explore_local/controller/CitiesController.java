package org.example.explore_local.controller;

import org.example.explore_local.model.entity.City;
import org.example.explore_local.model.view.CityViewModel;
import org.example.explore_local.repository.BusinessRepository;
import org.example.explore_local.repository.CityRepository;
import org.example.explore_local.service.CityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/cities")
public class CitiesController {


    private final CityService cityService;
    private final CityRepository cityRepository;
    private final BusinessRepository businessRepository;

    public CitiesController(CityService cityService, CityRepository cityRepository, BusinessRepository businessRepository) {
        this.cityService = cityService;
        this.cityRepository = cityRepository;
        this.businessRepository = businessRepository;
    }


    @ModelAttribute()
    public CityViewModel cityViewModel() {
        return new CityViewModel();
    }


    @GetMapping("/all")
    public String allCities(Model model) {

        model.addAttribute("allCities", cityService.getAllCitiesViewModels());
//        model.addAttribute("mostPopular", cityService.getSixMostPopularCitiesViewModels());
        return "cities";
    }

    @GetMapping("/city/{id}")
    public ModelAndView goToCity(@PathVariable("id") long id, Model model) {


        CityViewModel cityView = cityService.getCityViewModelById(id);
        City city = cityService.getCityById(id);

        ModelAndView modelAndView = new ModelAndView("city");
        modelAndView.addObject("cityProfile", cityView);
        modelAndView.addObject("allBusinesses", businessRepository.getBusinessesByCity(city));

        return modelAndView;

    }


}
