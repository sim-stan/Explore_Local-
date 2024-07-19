package org.example.explore_local.service;


import org.example.explore_local.errors.UserNotFoundException;
import org.example.explore_local.model.dtos.UserEditProfileDTO;
import org.example.explore_local.model.dtos.UserRegisterBindingModel;
import org.example.explore_local.model.entity.User;
import org.example.explore_local.model.enums.RoleName;
import org.example.explore_local.model.view.UserProfileViewModel;
import org.example.explore_local.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;
    private final UserHelperService userHelperService;
    private final UserDetailsService userDetailsService;


    public UserService(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, RoleService roleService, UserHelperService userHelperService, UserDetailsService userDetailsService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
        this.userHelperService = userHelperService;
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

        user.getRoles().add(roleService.findByName(RoleName.USER));
        userRepository.save(user);
    }

    public Authentication login(String username) {

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        org.springframework.security.core.Authentication auth = new UsernamePasswordAuthenticationToken(
                userDetails,
                userDetails.getPassword(),
                userDetails.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(auth);

        return auth;

    }

    public UserEditProfileDTO getProfileData(){

        return modelMapper.map(userHelperService.getUser(),UserEditProfileDTO.class);
    }

    public UserProfileViewModel getProfileView(){
        System.out.println(userHelperService.getUser());
        return modelMapper.map(userHelperService.getUser(),UserProfileViewModel.class);

    }




    public boolean isUniqueEmail(String email) {
        return this.userRepository.findByEmail(email).isEmpty();
    }

    public boolean isUniqueUsername(String username) {
        return this.userRepository.findByUsername(username).isEmpty();
    }

    public void editProfile(UserEditProfileDTO userEditProfileDTO) {
        Optional<User> userOptional = userRepository.findById(userHelperService.getUser().getId());
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            user.setUsername(userEditProfileDTO.getUsername());
            user.setEmail(userEditProfileDTO.getEmail());
            user.setFullName(userEditProfileDTO.getFullName());
            user.setPassword(passwordEncoder.encode(userEditProfileDTO.getPassword()));
            userRepository.save(user);
        }

    }


    public boolean isAuthorizedForUser(UserDetails userDetails, String username) {
        return !userDetails.getAuthorities().
                stream().
                filter(a -> a.getAuthority().equals("ROLE_USER")).
                collect(Collectors.toSet()).isEmpty() || username.equals(userDetails.getUsername());
    }

    public boolean isAuthorizedForBusiness(UserDetails userDetails, Long userId) {
        return !userDetails.getAuthorities().
                stream().
                filter(a -> a.getAuthority().equals("ROLE_BUSINESS_OWNER")).
                collect(Collectors.toSet()).isEmpty() || userRepository.findById(userId).map(user -> user.getUsername().equals(userDetails.getUsername())).orElse(false);
    }

    public UserEditProfileDTO getUserAndMapToProfileEditDTO(String username) {

       User user=userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User with username: " + username + " not found", username));
         return modelMapper.map(user,UserEditProfileDTO.class);
    }
}
