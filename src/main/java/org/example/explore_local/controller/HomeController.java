package org.example.explore_local.controller;

import org.example.explore_local.model.enums.CategoryName;
import org.example.explore_local.model.view.CityViewModel;
import org.example.explore_local.service.BusinessService;
import org.example.explore_local.service.CategoryService;
import org.example.explore_local.service.CityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller

public class HomeController {

    private final CityService cityService;
    private final BusinessService businessService;
    private final CategoryService categoryService;

    public HomeController(CityService cityService, BusinessService businessService, CategoryService categoryService) {
        this.cityService = cityService;
        this.businessService = businessService;
        this.categoryService = categoryService;
    }


@ModelAttribute("footerMostPopular")
public List<CityViewModel> footerMostPopular(){
        return cityService.getAllCitiesViewModels().stream().limit(5).toList();
}


    @GetMapping("/")
    public String home(Model model) {

        model.addAttribute("mostPopular",cityService.getAllCitiesViewModels().stream().limit(6).toList());
        model.addAttribute("footerMostPopular",cityService.getAllCitiesViewModels().stream().limit(5).toList());
        return "index";
    }


    @GetMapping("/about")
    public String about(Model model) {


        model.addAttribute("barsCounter",categoryService.getBusinessesByCategory(CategoryName.NIGHTLIFE).size());
        model.addAttribute("spaCounter",categoryService.getBusinessesByCategory(CategoryName.BEAUTY_SPA).size());
        model.addAttribute("restaurantCounter",categoryService.getBusinessesByCategory(CategoryName.RESTAURANTS).size());
        model.addAttribute("allBusinesses",businessService.getAllBusinessCount());
        return "about";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }


}
