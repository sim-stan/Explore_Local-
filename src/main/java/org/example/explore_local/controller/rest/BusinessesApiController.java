package org.example.explore_local.controller.rest;

import lombok.RequiredArgsConstructor;
import org.example.explore_local.model.view.BusinessProfileViewModel;
import org.example.explore_local.service.BusinessService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BusinessesApiController {

    private final BusinessService businessService;


    @GetMapping(value = "/all-businesses")
    public ResponseEntity<List<BusinessProfileViewModel>> getAllBusinesses(){
        return    ResponseEntity.status(200).body(businessService.getAllBusinessProfileViewModel());
    }

    @GetMapping("/all-businesses/{id}")
    public ResponseEntity<?> getBusinessById(@PathVariable long id){

        return ResponseEntity.status(200).body(businessService.getById(id));
    }
}
