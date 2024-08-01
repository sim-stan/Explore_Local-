package org.example.explore_local.model.entity;


import jakarta.persistence.*;
import org.example.explore_local.model.enums.RoleName;

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


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "author", cascade = CascadeType.ALL)
    private List<Review> reviews;



    @ElementCollection(targetClass = RoleName.class,fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private List<RoleName> roles;

    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
    private List<Business> ownedBusinesses;

    public User() {
        this.roles = new ArrayList<>();
        this.reviews = new ArrayList<>();
        this.ownedBusinesses = new ArrayList<>();
    }

    public User(long id, String username,
                String password, String email,
                String fullName, List<Review> reviews,
                List<RoleName> roles,
                List<Business> ownedBusinesses) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.fullName = fullName;
        this.reviews = reviews;
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

    public List<Review> getReviews() {
        return reviews;
    }

    public User setReviews(List<Review> reviews) {
        this.reviews = reviews;
        return this;
    }

    public List<RoleName> getRoles() {
        return roles;
    }

    public User setRoles(List<RoleName> roles) {
        this.roles = roles;
        return this;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", username='" + username + '\'' +
                ", password='" +( password !=null ? "N/A" : "[PROVIDED]") + '\'' +
                ", email='" + email + '\'' +
                ", fullName='" + fullName + '\'' +
                ", reviews=" + reviews.size() +
                ", roles=" + roles +
                ", ownedBusinesses=" + ownedBusinesses.size() +
                '}';
    }

}
