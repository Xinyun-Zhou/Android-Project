package com.example.mymarketplaceapp.models;


import java.util.List;

/**
 * User
 *
 * @author u7366711 Yuxuan Zhao
 */
public class User implements Comparable<User> {
    private int uid;
    private String username;
    private String password;
    private String postcode;
    private String address;
    private int phone;
    private List<CartItem> cart;

    public User(String username){
        this.username=username;
    }

    public User(int uid, String username, String password, String postcode, String address, int phone, List<CartItem> cart) {
        this.uid = uid;
        this.username = username;
        this.password = password;
        this.postcode = postcode;
        this.address = address;
        this.phone = phone;
        this.cart = cart;
    }

    public User() {
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

    public List<CartItem> getCart() {
        return cart;
    }

    public void setCart(List<CartItem> cart) {
        this.cart = cart;
    }

    @Override
    public int compareTo(User o) {
        int strDifference = this.username.compareTo(o.username);
        if (strDifference < 0) {
            return -1;
        } else if (strDifference > 0) {
            return 1;
        } else {
            return 0;
        }
    }
}
