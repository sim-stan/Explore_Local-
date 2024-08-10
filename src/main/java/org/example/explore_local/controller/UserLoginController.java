package org.example.explore_local.controller;

import org.example.explore_local.model.dtos.UserLoginBindingModel;
import org.example.explore_local.repository.UserRepository;
import org.example.explore_local.service.UserHelperService;
import org.example.explore_local.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserLoginController {


    private final UserService userService;
    private final UserRepository userRepository;
    private final UserHelperService userHelperService;

    public UserLoginController(UserService userService, UserRepository userRepository, UserHelperService userHelperService) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.userHelperService = userHelperService;
    }


    @ModelAttribute
    public UserLoginBindingModel userLoginBindingModel() {
        return new UserLoginBindingModel();
    }

    @GetMapping("/login")
    public String viewLogin(Model model) {


        return "login";
    }

    @GetMapping("/login-error")
    public String loginError(Model model) {

        model.addAttribute("bad_credentials", true);

        return "login";
    }
}
