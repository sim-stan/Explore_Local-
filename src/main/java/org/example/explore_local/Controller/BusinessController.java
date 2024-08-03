package org.example.explore_local.Controller;

import jakarta.validation.Valid;
import org.example.explore_local.model.dtos.BusinessEditProfileDTO;
import org.example.explore_local.model.dtos.BusinessRegisterBindingModel;
import org.example.explore_local.model.enums.CategoryName;
import org.example.explore_local.model.view.BusinessProfileViewModel;
import org.example.explore_local.service.BusinessService;
import org.example.explore_local.service.CategoryService;
import org.example.explore_local.service.CityService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;

@Controller
@RequestMapping("/businesses")
public class BusinessController {

    private final BusinessService businessService;
    private final CityService cityService;
    private final CategoryService categoryService;


    public BusinessController(BusinessService businessService, CityService cityService, CategoryService categoryService) {
        this.businessService = businessService;
        this.cityService = cityService;
        this.categoryService = categoryService;

    }

    @ModelAttribute("businessRegister")
    public BusinessRegisterBindingModel businessRegister(){
        return new BusinessRegisterBindingModel();
    }

    @ModelAttribute("businessEditProfile")
    public BusinessEditProfileDTO businessEditProfile(){
        return new BusinessEditProfileDTO();
    }

    @ModelAttribute("allCategories")
    public CategoryName[] allCategories(){
        return CategoryName.values();
    }

    @GetMapping("/add-business")
    public String addBusiness(Model model) {

        model.addAttribute("cities", cityService.getAllCitiesViewModels());

        System.out.println(Arrays.toString(CategoryName.values()));
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

        BusinessProfileViewModel profileView = businessService.getBusinessProfileView(id);
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