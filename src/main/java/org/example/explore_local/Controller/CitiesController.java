package org.example.explore_local.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CitiesController {

    @GetMapping("/cities")
    public String allCities(){
        return "cities";
    }
}
