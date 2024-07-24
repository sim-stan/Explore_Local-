package org.example.explore_local.Controller;

import jakarta.validation.Valid;
import org.example.explore_local.model.dtos.BusinessEditProfileDTO;
import org.example.explore_local.model.dtos.BusinessRegisterBindingModel;
import org.example.explore_local.model.view.BusinessProfileViewModel;
import org.example.explore_local.service.BusinessService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/businesses")
public class BusinessController {

    private final BusinessService businessService;

    public BusinessController(BusinessService businessService) {
        this.businessService = businessService;
    }

    @ModelAttribute("businessRegister")
    public BusinessRegisterBindingModel businessRegister(){
        return new BusinessRegisterBindingModel();
    }

    @ModelAttribute("businessEditProfile")
    public BusinessEditProfileDTO businessEditProfile(){
        return new BusinessEditProfileDTO();
    }

    @GetMapping("/add-business")
    public String addBusiness() {
        return "add_business";
    }

    @PostMapping("/add-business")
    public String addBusiness(  @Valid BusinessRegisterBindingModel businessRegisterBindingModel,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes,
                                @AuthenticationPrincipal UserDetails businessOwner){

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("businessRegisterBindingModel", businessRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.createOfferDTO", bindingResult);
            return "redirect:/businesses/add-business";
        }

        long id=businessService.addBusiness(businessRegisterBindingModel,businessOwner);

        return "redirect:/businesses/" + id;
    }

    @GetMapping("/{id}")
    public String viewBusiness(@PathVariable("id") long id, Model model,
                                 @AuthenticationPrincipal UserDetails principal) {

        BusinessProfileViewModel profileView = businessService.getProfileView(id);
        model.addAttribute("userProfile", profileView);


        return "redirect:/businesses/" + id;
    }

    @GetMapping("/edit-business")
    public String editBusiness() {
        return "edit_business";
    }


    @PreAuthorize("@businessService.isOwner(#id,#principal.username)")
    @DeleteMapping("/{id}")
    public String deleteBusiness(@PathVariable("id") long id,
                         @AuthenticationPrincipal UserDetails principal) {

        businessService.deleteBusinessById(id);

        return "redirect:/";
    }

    @GetMapping("/all")
    public String viewAllBusiness(){
        return "view_businesses";
    }
}