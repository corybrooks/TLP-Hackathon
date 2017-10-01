package com.example.rickjames.eraticators.model;

/**
 * Created by RickJames on 10/1/2017.
 */

public class User {

    private String name;
    private UserType user;
    private String email;
    private String password;

    public User(String name, UserType user, String email, String password) {
        this.name = name;
        this.user = user;
        this.email = email;
        this. password = password;
    }

    public String getName() { return this.name; }
    public void setName(String name) {
        this.name = name;
    }

    public UserType getUser() { return this.user; }
    public void setUser(UserType user) {
        this.user = user;
    }

    public String getEmail() { return this.email; }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() { return this.password; }
    public void setPassword(String password) {
        this.password = password;
    }

}
