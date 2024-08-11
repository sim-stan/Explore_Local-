package org.example.explore_local.controller;

import jakarta.validation.Valid;
import org.example.explore_local.model.dtos.UserRegisterBindingModel;
import org.example.explore_local.repository.UserRepository;
import org.example.explore_local.service.UserHelperService;
import org.example.explore_local.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class UserRegisterController {


    private final UserService userService;
    private final UserRepository userRepository;
    private final UserHelperService userHelperService;

    public UserRegisterController(UserService userService, UserRepository userRepository, UserHelperService userHelperService) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.userHelperService = userHelperService;
    }


    @ModelAttribute
    public UserRegisterBindingModel userRegisterBindingModel() {
        return new UserRegisterBindingModel();
    }

    @GetMapping("/register")
    public String register() {

        
        return "register";
    }


    @PostMapping("/register")
    public String register(@Valid UserRegisterBindingModel userRegisterBindingModel,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            final String attributeName = "userRegisterBindingModel";
            redirectAttributes
                    .addFlashAttribute(attributeName, userRegisterBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);
            return "redirect:/users/register";

        } else {

            this.userService.register(userRegisterBindingModel);
            return "redirect:/users/login";
        }


    }

}
