package org.example.explore_local.service;


import org.example.explore_local.model.entity.User;
import org.example.explore_local.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserHelperService {


    private final String ROLE_PREFIX="ROLE_";

    private final UserRepository userRepository;

    public UserHelperService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User getUser(){
        String username= getUserDetails().getUsername();
        return userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("User with username: " + username + " was not found"));
    }

    public boolean isAuthenticated(){
        return !hasRole("ANONYMOUS");
    }



    public boolean hasRole(String role){
        return getAuthentication().getAuthorities().
                stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(ROLE_PREFIX + role));
    }

    public UserDetails getUserDetails(){
        return (UserDetails) getAuthentication().getPrincipal();
    }

    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();

    }


}
