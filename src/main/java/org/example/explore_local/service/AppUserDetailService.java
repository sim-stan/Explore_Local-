package org.example.explore_local.service;


import org.example.explore_local.model.entity.User;
import org.example.explore_local.model.enums.RoleName;
import org.example.explore_local.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;
import java.util.stream.Collectors;

public class AppUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;


    public AppUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);

        return user
                .map(AppUserDetailService::map)
                .orElseThrow(() -> new UsernameNotFoundException("User with username: " + username + " not found"));

        //        Cookie cookie = new Cookie("jwt", jwtService.generateToken(user.get()));
//        cookie.setPath("/");
//        cookie.setHttpOnly(true);
//        response.addCookie(cookie);
    }

    private static UserDetails map(User user) {
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream().map(AppUserDetailService::map).collect(Collectors.toList())
        );
    }

    private static GrantedAuthority map(RoleName role) {
        return new SimpleGrantedAuthority(
                "ROLE_" + role.name()
        );
    }
}
