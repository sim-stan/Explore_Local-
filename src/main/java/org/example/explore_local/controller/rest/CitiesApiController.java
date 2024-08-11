package org.example.explore_local.controller.rest;

import lombok.RequiredArgsConstructor;
import org.example.explore_local.model.view.CityViewModel;
import org.example.explore_local.service.CityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CitiesApiController {

    private final CityService cityService;


    @GetMapping(value = "/all-cities")
    public ResponseEntity<List<CityViewModel>> getAllCities(){
        return    ResponseEntity.status(200).body(cityService.getAllCitiesViewModels());
    }

    @GetMapping("/all-cities/{id}")
    public ResponseEntity<?> getCityById(@PathVariable long id){
        return ResponseEntity.status(200).body(cityService.getCityById(id));
    }
}
