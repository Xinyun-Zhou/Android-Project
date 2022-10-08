package com.example.mymarketplaceapp.models;


/**
 * User
 *
 * @author u7366711 Yuxuan Zhao
 */
public class User {
    private int uid;
    private String username;
    private String password;

    public User(int uid, String username, String password) {
        this.uid = uid;
        this.username = username;
        this.password = password;
    }

    public int getUid() {
        return uid;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
