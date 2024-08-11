package org.example.explore_local.model.view;

import org.example.explore_local.model.entity.Category;

public class BusinessProfileViewModel {


    private long id;
    private String name;


    private Category category;


    private String email;


    private String phoneNumber;


    private String address;


    private String about;

    public BusinessProfileViewModel() {
    }

    public BusinessProfileViewModel(long id, String name, Category category, String email, String phoneNumber, String address, String about) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.about = about;
    }

    public String getName() {
        return name;
    }

    public BusinessProfileViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public Category getCategory() {
        return category;
    }

    public BusinessProfileViewModel setCategory(Category category) {
        this.category = category;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public BusinessProfileViewModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public BusinessProfileViewModel setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public BusinessProfileViewModel setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getAbout() {
        return about;
    }

    public BusinessProfileViewModel setAbout(String about) {
        this.about = about;
        return this;
    }

    public long getId() {
        return id;
    }

    public BusinessProfileViewModel setId(long id) {
        this.id = id;
        return this;
    }
}
