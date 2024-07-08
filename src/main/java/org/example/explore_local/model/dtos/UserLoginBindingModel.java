package org.example.explore_local.model.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.example.explore_local.vallidation.anotations.UniqueUsername;

public class UserLoginBindingModel {

    @NotNull
    @Size(min = 2, message = "{user.username.length}")
    private String username;

    @NotNull
    @Size(min = 2, message = "{user.full-name.length}")
    private String password;

    public UserLoginBindingModel() {
    }

    public String getUsername() {
        return username;
    }

    public UserLoginBindingModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserLoginBindingModel setPassword(String password) {
        this.password = password;
        return this;
    }
}
