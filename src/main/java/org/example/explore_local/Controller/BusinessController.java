package org.example.explore_local.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/businesses")
public class BusinessController {

    @GetMapping("/add-business")
    public String addBusiness() {
        return "add_business";
    }


}