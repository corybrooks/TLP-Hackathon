package com.example.rickjames.eraticators.model;

import java.util.Arrays;
import java.util.List;

/**
 * Created by RickJames on 10/1/2017.
 */

public class User {

    private String name;
    private UserType user;
    private String email;
    private String password;

    private static final List<UserType> legalUser = Arrays.asList(UserType.values());

    /**
     * Contructor to create a new user
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
     * @param name the name of the user
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return the type of the user
     */
    public UserType getUser() { return this.user; }

    /**
     *
     * @return the type of the user
     */
    public void setUser(UserType user) {
        this.user = user;
    }

    /**
     *
     * @return the email of the user
     */
    public String getEmail() { return this.email; }

    /**
     *
     * @return the email of the user
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return the password of the user
     */
    public String getPassword() { return this.password; }

    /**
     *
     * @return the password of the user
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Finds the position of the user spinner
     * @param code The value of the current selection of the user spinner.
     * @return 0
     */
    public static int findPosition(String code) {
        int i = 0;
        while (i < legalUser.size()) {
            if (code.equals(legalUser.get(i).toString())) return i;
            ++i;
        }
        return 0;
    }
}
