package org.example.explore_local.controller.rest;

import lombok.RequiredArgsConstructor;
import org.example.explore_local.model.entity.User;
import org.example.explore_local.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UsersApiController {

    private final UserService userService;


    @GetMapping(value = "/all-users")
    public ResponseEntity<List<User>> getAllUsers(){
     return    ResponseEntity.status(200).body(userService.getAllUsers());
    }

    @GetMapping("/all-users/{id}")
    public ResponseEntity<?> getUserById(@PathVariable long id){
        return ResponseEntity.status(200).body(userService.getUserProfileViewModelById(id));
    }
}
