package com.example.rickjames.eraticators.model;

import java.io.Serializable;

/**
 * Created by RickJames on 10/1/2017.
 */

public enum UserType implements Serializable {
    USER("User"),
    ADMIN("Admin");

    private String userType;

    UserType(String userType) {
        this.userType = userType;
    }

    String getUserType() {
        return this.userType;
    }
}
