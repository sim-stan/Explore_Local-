package org.example.explore_local.model.dtos;

import jakarta.validation.constraints.NotBlank;
import org.example.explore_local.model.entity.Category;
import org.example.explore_local.vallidation.anotations.UniqueBusinessName;
import org.example.explore_local.vallidation.anotations.UniqueEmail;

import javax.validation.constraints.NotNull;

public class BusinessEditProfileDTO {


    private Long id;
    @NotBlank
    @UniqueBusinessName
    private String name;


    @NotNull
    private Category category;
    @NotNull
    @UniqueEmail
    private String email;


    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String address;


    @NotBlank
    private String about;

    public BusinessEditProfileDTO() {
    }

    public String getName() {
        return name;
    }

    public BusinessEditProfileDTO setName(String name) {
        this.name = name;
        return this;
    }

    public Category getCategory() {
        return category;
    }

    public BusinessEditProfileDTO setCategory(Category category) {
        this.category = category;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public BusinessEditProfileDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public BusinessEditProfileDTO setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public BusinessEditProfileDTO setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getAbout() {
        return about;
    }

    public BusinessEditProfileDTO setAbout(String about) {
        this.about = about;
        return this;
    }

    public Long getId() {
        return id;
    }

    public BusinessEditProfileDTO setId(Long id) {
        this.id = id;
        return this;
    }
}
