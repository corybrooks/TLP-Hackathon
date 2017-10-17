package com.example.rickjames.eraticators.model;

import java.io.Serializable;

/**
 * Created by RickJames on 10/1/2017.
 */

public enum UserType implements Serializable {
    USER("User"),
    ADMIN("Admin");

    private String userType;

    /**
     * Contructor for UserType enum
     * @param userType sets the user's initial type
     */
    UserType(String userType) {
        this.userType = userType;
    }

    /**
     *
     * @return returns the user's type
     */
    String getUserType() {
        return this.userType;
    }

    @Override
    public String toString() {
        return getUserType();
    }
}
