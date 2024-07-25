package org.example.explore_local.Controller;

import org.example.explore_local.model.view.CityViewModel;
import org.example.explore_local.service.CitiesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cities")
public class CitiesController {


    private final CitiesService citiesService;

    public CitiesController(CitiesService citiesService) {
        this.citiesService = citiesService;
    }


    @ModelAttribute()
    public CityViewModel cityViewModel() {
        return new CityViewModel();
    }


    @GetMapping("/all")
    public String allCities(Model model) {

        model.addAttribute("allCities", citiesService.getAllCitiesViewModels());
        return "cities";
    }


}
