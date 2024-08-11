package org.example.explore_local.controller;

import org.example.explore_local.model.dtos.UserLoginBindingModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserLoginController {

    @ModelAttribute
    public UserLoginBindingModel userLoginBindingModel() {
        return new UserLoginBindingModel();
    }

    @GetMapping("/login")
    public String viewLogin() {
        return "login";
    }


    @GetMapping("/login-error")
    public String loginError(Model model) {



        model.addAttribute("showErrorMessage", true);
        model.addAttribute("loginData", new UserLoginBindingModel());

        return "login";

    }
}
