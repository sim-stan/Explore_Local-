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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    private final BusinessService businessService;
    private final UserHelperService userHelperService;
    private final UserDetailsService userDetailsService;


    public UserService(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, BusinessService businessService, UserHelperService userHelperService, UserDetailsService userDetailsService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.businessService = businessService;
        this.userHelperService = userHelperService;
        this.userDetailsService = userDetailsService;
    }


    public List<User> getAllUsers(){
        return userRepository.findAll();
    }


    public UserProfileViewModel mapUserToProfileViewModel(User user) {
        return modelMapper.map(user, UserProfileViewModel.class);

    }



    public void register(UserRegisterBindingModel userRegisterBindingModel) {

        User user = this.modelMapper.map(userRegisterBindingModel, User.class);

        if (!isUniqueEmail(user.getEmail())) {
            throw new RuntimeException("Email is taken");
        } else if (!isUniqueUsername(user.getUsername())) {
            throw new RuntimeException("Username is taken");
        }
        user.setPassword(passwordEncoder.encode(userRegisterBindingModel.getPassword()));
        user.getRoles().add(RoleName.USER);
        userRepository.save(user);
    }

    public Authentication login(String username) {

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        Authentication auth = new UsernamePasswordAuthenticationToken(
                userDetails,
                userDetails.getPassword(),
                userDetails.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(auth);

        return auth;

    }

//    public UserEditProfileDTO getProfileData(long id) {
//        Optional<User> byId = userRepository.findById(id);
//        return modelMapper.map(byId, UserEditProfileDTO.class);
//    }

    public UserProfileViewModel getUserProfileViewModelById(long id) {
        Optional<User> byId = userRepository.findById(id);
        return modelMapper.map(byId, UserProfileViewModel.class);
    }

    public UserProfileViewModel getProfileView() {
        return modelMapper.map(userHelperService.getUser(), UserProfileViewModel.class);

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

    public void deleteUser(long id) {
        userRepository.findById(id).ifPresent(user -> {
            user.getOwnedBusinesses().
                    forEach(businessService::deleteBusiness);
            userRepository.delete(user);
        });


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

        User user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User with username: " + username + " not found", username));
        return modelMapper.map(user, UserEditProfileDTO.class);
    }

    public void seedUserAndAdmin() {


        if (userRepository.count() <= 0) {
            User testUser =new User();

            testUser.setFullName("John Smith");
            testUser.setEmail("john@smith.com");
            testUser.setUsername("john");
            testUser.setPassword(passwordEncoder.encode("123"));
            testUser.getRoles().add(RoleName.USER);
            userRepository.save(testUser);



            User testAdmin =new User();

            testAdmin.setFullName("Admin Admin");
            testAdmin.setEmail("admin@admin.com");
            testAdmin.setUsername("admin");
            testAdmin.setPassword(passwordEncoder.encode("123"));
            testAdmin.getRoles().add(RoleName.ADMIN);
            userRepository.save(testAdmin);
        }
    }


}
