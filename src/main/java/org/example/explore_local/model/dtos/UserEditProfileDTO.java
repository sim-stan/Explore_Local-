package org.example.explore_local.model.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.example.explore_local.vallidation.anotations.ValidNewEmailAndUsername;

@ValidNewEmailAndUsername(
        email = "email",
        id = "id",
        username = "username"
)
public class UserEditProfileDTO {


    private Long id;
    @NotNull
    @Size(min = 2, message = "{user.username.length}")
    private String username;

    @NotNull
    @Size(min = 2, message = "{user.full-name.length}")
    private String fullName;

    @NotNull
    @Email(regexp = ".+[@].+", message = "{user.email}")
    private String email;


    @Size(min = 2, message = "{user.password.length}")
    private String password;

    @Size(min = 2, message = "{user.confirm-password.length}")
    private String confirmPassword;

    public UserEditProfileDTO() {
    }

    public String getUsername() {
        return username;
    }

    public UserEditProfileDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public UserEditProfileDTO setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserEditProfileDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserEditProfileDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }


    public UserEditProfileDTO setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }

    public Long getId() {
        return id;
    }

    public UserEditProfileDTO setId(Long id) {
        this.id = id;
        return this;
    }

 }
