package org.example.explore_local.Controller;

import jakarta.validation.Valid;
import org.example.explore_local.model.dtos.UserLoginBindingModel;
import org.example.explore_local.model.dtos.UserRegisterBindingModel;
import org.example.explore_local.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class AuthController {


    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }


    @ModelAttribute
    public UserRegisterBindingModel userRegisterBindingModel(){
        return new UserRegisterBindingModel();
    }
    @ModelAttribute
    public UserLoginBindingModel userLoginBindingModel(){
        return new UserLoginBindingModel();
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @PostMapping("/login")
    public String onFailure(@ModelAttribute("username") String username, Model model) {


        return "index";
    }


    @GetMapping("/register")
    public String register(){
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
                    .addFlashAttribute("org.springframework.validation.BindingResult", bindingResult);
           return "redirect:/users/register";

        } else {

            this.userService.register(userRegisterBindingModel);
           return "redirect:/users/login";
        }


    }


}
