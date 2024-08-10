package org.example.explore_local.model.dtos;


import jakarta.validation.constraints.NotBlank;
import org.example.explore_local.vallidation.anotations.UniqueBusinessName;
import org.example.explore_local.vallidation.anotations.UniqueEmail;

import javax.validation.constraints.NotNull;

public class  BusinessRegisterBindingModel {


    @NotNull
    @UniqueBusinessName
    private String name;


    @NotNull
    private String categoryName;
    @NotNull
    @UniqueEmail
    private String email;


    @NotNull
    private String phoneNumber;

    @NotNull
    private String address;


    @NotNull
    private String cityName;


    @NotBlank
    private String about;

    public BusinessRegisterBindingModel() {
    }

    public String getName() {
        return name;
    }

    public BusinessRegisterBindingModel setName(String name) {
        this.name = name;
        return this;
    }


    public String getEmail() {
        return email;
    }

    public BusinessRegisterBindingModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public BusinessRegisterBindingModel setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public BusinessRegisterBindingModel setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getAbout() {
        return about;
    }

    public BusinessRegisterBindingModel setAbout(String about) {
        this.about = about;
        return this;
    }


    public String getCityName() {
        return cityName;
    }

    public BusinessRegisterBindingModel setCityName(String cityName) {
        this.cityName = cityName;
        return this;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
