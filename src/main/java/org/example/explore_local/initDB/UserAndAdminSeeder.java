package org.example.explore_local.initDB;

import org.example.explore_local.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UserAndAdminSeeder implements CommandLineRunner {

    private final UserService userService;


    public UserAndAdminSeeder( UserService userService) {
        this.userService = userService;
    }


    @Override
    public void run(String... args) throws Exception {
        userService.seedUserAndAdmin();
    }
}
