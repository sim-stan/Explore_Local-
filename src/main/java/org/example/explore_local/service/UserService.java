package org.example.explore_local.service;


import org.example.explore_local.model.dtos.UserLoginBindingModel;
import org.example.explore_local.model.dtos.UserRegisterBindingModel;
import org.example.explore_local.model.entity.User;
import org.example.explore_local.model.enums.RoleName;
import org.example.explore_local.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;
    private final UserDetailsService userDetailsService;


    public UserService(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, RoleService roleService, UserDetailsService userDetailsService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
        this.userDetailsService = userDetailsService;
    }

    public void register(UserRegisterBindingModel userRegisterBindingModel) {

        User user = this.modelMapper.map(userRegisterBindingModel, User.class);

        if (!isUniqueEmail(user.getEmail())) {
            throw new RuntimeException("Email is taken");
        } else if (!isUniqueUsername(user.getUsername())) {
            throw new RuntimeException("Username is taken");
        }
        user.setPassword(passwordEncoder.encode(userRegisterBindingModel.getPassword()));
        user.setActive(false);
        user.getRoles().add(roleService.findByName(RoleName.USER));
        userRepository.save(user);
    }

    public Authentication login(UserLoginBindingModel userLoginBindingModel) {

        UserDetails userDetails = userDetailsService.loadUserByUsername(userLoginBindingModel.getUsername());

        org.springframework.security.core.Authentication auth = new UsernamePasswordAuthenticationToken(
                userDetails,
                userDetails.getPassword(),
                userDetails.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(auth);

        return auth;

    }


    public boolean isUniqueEmail(String email) {
        return this.userRepository.findByEmail(email).isEmpty();
    }

    public boolean isUniqueUsername(String username) {
        return this.userRepository.findByUsername(username).isEmpty();
    }
}
