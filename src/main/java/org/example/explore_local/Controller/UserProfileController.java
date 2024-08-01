package org.example.explore_local.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.example.explore_local.model.dtos.UserEditProfileDTO;
import org.example.explore_local.model.view.UserProfileViewModel;
import org.example.explore_local.repository.UserRepository;
import org.example.explore_local.service.UserHelperService;
import org.example.explore_local.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class UserProfileController {


    private final UserService userService;
    private final UserRepository userRepository;
    private final UserHelperService userHelperService;

    public UserProfileController(UserService userService, UserRepository userRepository, UserHelperService userHelperService) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.userHelperService = userHelperService;
    }


    @ModelAttribute
    public UserEditProfileDTO userEditProfileDTO() {
        return new UserEditProfileDTO();
    }

    @ModelAttribute
    public UserProfileViewModel userProfileViewModel() {
        return new UserProfileViewModel();
    }


    @GetMapping("/profile")

    public String viewProfile(Model model, RedirectAttributes redirectAttributes,
                              @AuthenticationPrincipal UserDetails userDetails) {


        UserProfileViewModel profileView = userService.getProfileView();
        model.addAttribute("userProfile", profileView);

        return "profile";
    }


    @GetMapping("/edit-profile/{username}")
    public String viewEditProfile(@PathVariable String username,
                                  Model model,

                                  @AuthenticationPrincipal UserDetails userDetails) {

        if (username == null) {
            return "redirect:/";
        }

        checkIfAuthorized(userDetails, username);

        if (isValidUser(username)) {

            UserEditProfileDTO profileEditDTO = userService.getUserAndMapToProfileEditDTO(username);
            model.addAttribute("profileEditDTO", profileEditDTO);

        }
        return "edit-profile";
    }

    @PostMapping("/edit-profile/{username}")
    public String postProfile(@Valid UserEditProfileDTO userEditProfileDTO, BindingResult bindingResult,
                              RedirectAttributes redirectAttributes,
                              @AuthenticationPrincipal UserDetails userDetails,
                              @PathVariable String username) {

        checkIfAuthorized(userDetails, username);

        if (isValidUser(username)) {
            if (bindingResult.hasErrors()) {
                redirectAttributes.addFlashAttribute("profileEditDTO", userEditProfileDTO);
                redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.profileEditDTO", bindingResult);
                return "redirect:/users/edit-profile/" + username;
            }
            userService.editProfile(userEditProfileDTO);
        }
        return "redirect:/";
    }

    @DeleteMapping("/delete-account/{id}")
    public ModelAndView deleteUser(@PathVariable long id, HttpServletRequest request, HttpServletResponse response) {
        userService.deleteUser(id);

        SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
        //Second option !!
       // SecurityContextHolder.clearContext();
        return new ModelAndView("index");

    }


//    @ResponseStatus(HttpStatus.UNAUTHORIZED)
//    @ExceptionHandler(UnauthorizedException.class)
//    public ModelAndView notAuthorized(UnauthorizedException uae) {
//        return new ModelAndView("error/unauthorized");
//    }


    private boolean isValidUser(String username) {
        if (userRepository.findByUsername(username).isEmpty()) {
            throw new UsernameNotFoundException("User with " + username + " not found");
        }
        return true;
    }


    private void checkIfAuthorized(UserDetails userDetails, String username) {
        if (!userService.isAuthorizedForUser(userDetails, username)) {
            throw new RuntimeException("Access denied ");
        }
    }
}
