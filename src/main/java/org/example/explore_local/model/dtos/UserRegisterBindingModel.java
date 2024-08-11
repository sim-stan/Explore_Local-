package org.example.explore_local.model.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.explore_local.vallidation.anotations.PasswordMatch;
import org.example.explore_local.vallidation.anotations.UniqueEmail;
import org.example.explore_local.vallidation.anotations.UniqueUsername;

@NoArgsConstructor
@Getter
@Setter
@PasswordMatch
public class UserRegisterBindingModel {

    @NotNull(message = "Username can not be null")
    @UniqueUsername
    @Size(min = 2, message = "Username must be at least 2 characters long")
    private String username;

    @NotNull(message = "Full Name can not be null")
    @Size(min = 2, max = 200 ,message = "Username must be between 2 and 200 characters long")
    private String fullName;

    @NotNull
    @UniqueEmail
    @Email(regexp = ".+[@].+", message = "Must be a valid email address")
    private String email;

    @Size(min = 2,  message = "Password must be at least 2 characters long")
    private String password;

    @Size(min = 2,  message = "Password must be at least 2 characters long")
    private String confirmPassword;



    @Override
    public String toString() {
        return "UserRegisterBindingModel{" +
                "username='" + username + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
