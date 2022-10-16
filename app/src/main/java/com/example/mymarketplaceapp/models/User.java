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
    private String postcode;
    private String address;
    private int phone;

    public User(int uid, String username, String password, String postcode, String address, int phone) {
        this.uid = uid;
        this.username = username;
        this.password = password;
        this.postcode = postcode;
        this.address = address;
        this.phone = phone;
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

    public String getPostcode() {
        return postcode;
    }

    public String getAddress() {
        return address;
    }

    public int getPhone() {
        return phone;
    }
}
