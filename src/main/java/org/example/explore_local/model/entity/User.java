package org.example.explore_local.model.entity;


import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;


    @Column(unique = true)
    private String email;

    @Column(name = "full_name")
    private String fullName;

    @Column
    private boolean isActive;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "author", cascade = CascadeType.ALL)
    private List<Review> reviews;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "author", cascade = CascadeType.ALL)
    private List<Picture> pictures;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
    )
    private List<Role> roles;

    @OneToMany(mappedBy = "owner")
    private List<Business> ownedBusinesses;

    public User() {
        this.roles = new ArrayList<>();
        this.reviews =new ArrayList<>();
        this.pictures=new ArrayList<>();
        this.ownedBusinesses=new ArrayList<>();
    }

    public User(long id, String username,
                String password, String email,
                String fullName, boolean isActive, List<Review> reviews,
                List<Picture> pictures, List<Role> roles,
                List<Business> ownedBusinesses) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.fullName = fullName;
        this.isActive = isActive;
        this.reviews = reviews;
        this.pictures = pictures;
        this.roles = roles;
        this.ownedBusinesses = ownedBusinesses;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getFullName() {
        return fullName;
    }

    public User setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }


    public List<Role> getRoles() {
        return roles;
    }

    public User setRoles(List<Role> roles) {
        this.roles = roles;
        return this;
    }

    public List<Business> getOwnedBusinesses() {
        return ownedBusinesses;
    }

    public User setOwnedBusinesses(List<Business> ownedBusinesses) {
        this.ownedBusinesses = ownedBusinesses;
        return this;
    }


    public List<Review> getComments() {
        return reviews;
    }

    public User setComments(List<Review> reviews) {
        this.reviews = reviews;
        return this;
    }

    public List<Picture> getPictures() {
        return pictures;
    }

    public User setPictures(List<Picture> pictures) {
        this.pictures = pictures;
        return this;
    }

    public boolean isActive() {
        return isActive;
    }

    public User setActive(boolean active) {
        isActive = active;
        return this;
    }
}
