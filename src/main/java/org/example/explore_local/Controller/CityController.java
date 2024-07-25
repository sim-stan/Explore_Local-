package org.example.explore_local.Controller;

import org.example.explore_local.model.dtos.CityDTO;
import org.example.explore_local.service.CitiesService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CityController {

    private final CitiesService citiesService;

    public CityController(CitiesService citiesService) {
        this.citiesService = citiesService;
    }


    @ModelAttribute
    public CityDTO cityDTO(){
        return new CityDTO();
    }

    @GetMapping("/cities/add-city")
    public String viewAddCity() {

        return "add_city";
    }

    @PostMapping("/cities/add-city")

    public String addCity(CityDTO cityDTO, BindingResult bindingResult,  RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()) {

            redirectAttributes
                    .addFlashAttribute("cityDTO", cityDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult", bindingResult);
            return "redirect:/cities/add-city";

        } else {

            this.citiesService.addCity(cityDTO);
            return "redirect:/";
        }
    }
}
