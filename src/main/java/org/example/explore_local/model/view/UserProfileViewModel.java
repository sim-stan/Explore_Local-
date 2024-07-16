package org.example.explore_local.model.view;

public class UserProfileViewModel {


    private long id;
    private String username;


    private String fullName;


    private String email;



    public UserProfileViewModel() {
    }

    public UserProfileViewModel(long id, String username, String fullName, String email) {
        this.id = id;
        this.username = username;
        this.fullName = fullName;
        this.email = email;


    }

    public String getUsername() {
        return username;
    }

    public UserProfileViewModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public UserProfileViewModel setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserProfileViewModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public long getId() {
        return id;
    }

    public UserProfileViewModel setId(long id) {
        this.id = id;
        return this;
    }


}
