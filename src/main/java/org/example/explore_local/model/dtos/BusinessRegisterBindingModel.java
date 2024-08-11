package org.example.explore_local.model.dtos;


import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.example.explore_local.vallidation.anotations.UniqueBusinessName;
import org.example.explore_local.vallidation.anotations.UniqueEmail;

import javax.validation.constraints.NotNull;

public class  BusinessRegisterBindingModel {


    @NotNull(message = "Business name can not be null")
    @UniqueBusinessName
    @Size(min = 2, message = "Business name must be at least 2 characters long")
    @Column(name = "business_name")
    private String businessName;


    @NotNull(message = "Category can not be null")
    private String categoryName;
    @NotNull
    @UniqueEmail
    @Email(regexp = ".+[@].+", message = "Must be a valid email address")
    private String email;


    @NotNull(message = "Business phone number can not be null")
    private String phoneNumber;

    @NotNull(message = "The address can not be null")
    private String address;


    @NotNull(message = "City can not be null")
    private String cityName;


    @NotBlank(message = "About can not be null")
    private String about;


    public BusinessRegisterBindingModel() {
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
}
