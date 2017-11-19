package com.example.rickjames.eraticators.model;

/**
 * Created by RickJames on 10/1/2017.
 */

public class User {

    private final String name;
    private final UserType user;
    private final String email;
    private final String password;

    /**
     * Constructor to create a new user
     * @param name The name of the new user
     * @param user the userType of the new user (User or Admin)
     * @param email the email of the new user
     * @param password the password of the new user
     */
    public User(String name, UserType user, String email, String password) {
        this.name = name;
        this.user = user;
        this.email = email;
        this. password = password;
    }

    /**
     *
     * @return the name of the user
     */
    public String getName() { return this.name; }

    /**
     *
     * @return the type of the user
     */
    public UserType getUser() { return this.user; }

    /**
     *
     * @return the email of the user
     */
    public String getEmail() { return this.email; }

    /**
     *
     * @return the password of the user
     */
    public String getPassword() { return this.password; }

}
