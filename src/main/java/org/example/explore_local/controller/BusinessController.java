package org.example.explore_local.controller;

import jakarta.validation.Valid;
import org.example.explore_local.model.dtos.BusinessEditProfileDTO;
import org.example.explore_local.model.dtos.BusinessRegisterBindingModel;
import org.example.explore_local.model.entity.Business;
import org.example.explore_local.model.enums.CategoryName;
import org.example.explore_local.repository.BusinessRepository;
import org.example.explore_local.repository.CategoryRepository;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/businesses")
public class BusinessController {

    private final BusinessService businessService;
    private final BusinessRepository businessRepository;
    private final CityService cityService;
    private final CategoryService categoryService;
    private final CategoryRepository categoryRepository;


    public BusinessController(BusinessService businessService, BusinessRepository businessRepository, CityService cityService, CategoryService categoryService, CategoryRepository categoryRepository) {
        this.businessService = businessService;
        this.businessRepository = businessRepository;
        this.cityService = cityService;
        this.categoryService = categoryService;

        this.categoryRepository = categoryRepository;
    }

    @ModelAttribute("businessRegister")
    public BusinessRegisterBindingModel businessRegister() {
        return new BusinessRegisterBindingModel();
    }

    @ModelAttribute("businessEditProfile")
    public BusinessEditProfileDTO businessEditProfile() {
        return new BusinessEditProfileDTO();
    }

    @ModelAttribute("allCategories")
    public CategoryName[] allCategories() {
        return CategoryName.values();
    }

    @GetMapping("/add-business")
    public String addBusiness(Model model) {

        model.addAttribute("cities", cityService.getAllCitiesViewModels());

        return "add_business";
    }

    @PostMapping("/add-business")
    public String addBusiness(@Valid BusinessRegisterBindingModel businessRegisterBindingModel,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes,
                              @AuthenticationPrincipal UserDetails businessOwner) {


        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("businessRegisterBindingModel", businessRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.createOfferDTO", bindingResult);
            return "redirect:/businesses/add-business";
        }

        long id = businessService.addBusiness(businessRegisterBindingModel, businessOwner);

        return "redirect:/businesses/business_detail/" + id;
    }


    @GetMapping("/business_detail/{id}")
    public ModelAndView viewBusiness(@PathVariable long id, Model model,
                                     @AuthenticationPrincipal UserDetails principal) {


        Business business = businessService.getById(id);

        if (business != null) {
            ModelAndView modelAndView = new ModelAndView("business_detail");
            modelAndView.addObject(business);
            model.addAttribute("profile", business);

            int size = business.getComments().size();
            model.addAttribute("rating", business.getComments());
            return modelAndView;

        }

        return new ModelAndView("redirect:/businesses/all");

    }

    @GetMapping("/edit-business")
    public String editBusiness() {
        return "edit-businessProfile";
    }


    @PreAuthorize("@businessService.isOwner(#id,#principal.username)")
    @DeleteMapping("/{id}")
    public String deleteBusiness(@PathVariable("id") long id,
                                 @AuthenticationPrincipal UserDetails principal) {

        businessService.deleteBusinessById(id);

        return "redirect:/";
    }

    @GetMapping("/all")
    public String viewAllBusiness(Model model) {

        model.addAttribute("allBusinesses", businessRepository.findAll());
        model.addAttribute("about", businessService.getAboutForAllBusinesses());
        return "view_businesses";
    }


}