package org.example.explore_local.model.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserLoginBindingModel {

    @NotNull(message = "Username can not be null")
    @Size(min = 2, message = "Username must be at least 2 characters long")
    private String username;

    @Size(min = 2,  message = "Password must be at least 2 characters long")
    private String password;


}
