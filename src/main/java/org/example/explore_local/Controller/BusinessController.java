package org.example.explore_local.Controller;

import jakarta.validation.Valid;
import org.example.explore_local.model.dtos.BusinessRegisterBindingModel;
import org.example.explore_local.service.BusinessService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
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

    @GetMapping("/add-business")
    public String addBusiness() {
        return "add_business";
    }

    @PostMapping("/add-business")
    public String addBusiness(  @Valid BusinessRegisterBindingModel businessRegisterBindingModel,
                                BindingResult bindingResult,
                                RedirectAttributes rAtt,
                                @AuthenticationPrincipal UserDetails businessOwner){

        if(bindingResult.hasErrors()){
            rAtt.addFlashAttribute("businessRegisterBindingModel", businessRegisterBindingModel);
            rAtt.addFlashAttribute("org.springframework.validation.BindingResult.createOfferDTO", bindingResult);
            return "redirect:/businesses/add-business";
        }

        long id=businessService.addBusiness(businessRegisterBindingModel,businessOwner);

        return "redirect:/businesses/" + id;
    }

    @PreAuthorize("@businessService.isOwner(#id,#principal.username)")
    @DeleteMapping("/{id}")
    public String deleteBusiness(@PathVariable("id") long id,
                         @AuthenticationPrincipal UserDetails principal) {

        businessService.deleteBusiness(id);

        return "redirect:/offers/all";
    }
}