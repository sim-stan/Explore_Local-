package org.example.explore_local.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "businesses")
public class Business {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String businessName;



    @ToString.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(
            name = "businesses_categories",
            joinColumns = {@JoinColumn(name = "business_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id", referencedColumnName = "id")})
    private Category category;

    @Column(unique = true)
    @Email
    private String email;


    @Column(nullable = false)
    private String phoneNumber;

    @NotBlank
    private String address;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    private City city;


    @NotBlank
    private String about;
    private String profilePicture;

    @ManyToOne
    private User owner;

    @OneToMany(mappedBy = "business",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Review> reviews;

    @OneToMany(mappedBy = "business",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Picture> pictures;

    private long likes;

    public Business() {

        this.reviews =new ArrayList<>();
        this.pictures=new ArrayList<>();
    }

    public Business(long id, String businessName, Category category,
                    String email, String phoneNumber, String address, City city,
                    String about, String profilePicture, User owner, List<Review> reviews,
                    List<Picture> pictures, long likes) {
        this.id = id;
        this.businessName = businessName;
        this.category = category;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.city = city;
        this.about = about;
        this.profilePicture = profilePicture;
        this.owner = owner;
        this.reviews = reviews;
        this.pictures = pictures;
        this.likes = likes;
    }

    public long getId() {
        return id;
    }

    public Business setId(long id) {
        this.id = id;
        return this;
    }

    public String getBusinessName() {
        return businessName;
    }

    public Business setBusinessName(String name) {
        this.businessName = name;
        return this;
    }

    public Category getCategory() {
        return category;
    }

    public Business setCategory(Category category) {
        this.category = category;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Business setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Business setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Business setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getAbout() {
        return about;
    }

    public Business setAbout(String about) {
        this.about = about;
        return this;
    }

    public User getOwner() {
        return owner;
    }

    public Business setOwner(User owner) {
        this.owner = owner;
        return this;
    }

    public List<Review> getComments() {
        return reviews;
    }

    public Business setComments(List<Review> reviews) {
        this.reviews = reviews;
        return this;
    }

    public List<Picture> getPictures() {
        return pictures;
    }

    public Business setPictures(List<Picture> pictures) {
        this.pictures = pictures;
        return this;
    }

    public long getLikes() {
        return likes;
    }

    public Business setLikes(long likes) {
        this.likes = likes;
        return this;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public Business setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
        return this;
    }

    public City getCity() {
        return city;
    }

    public Business setCity(City city) {
        this.city = city;
        return this;
    }

}
