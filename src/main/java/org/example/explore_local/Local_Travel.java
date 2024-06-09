package org.example.explore_local;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class Local_Travel {

    public static void main(String[] args) {
        SpringApplication.run(Local_Travel.class, args);
    }

}
