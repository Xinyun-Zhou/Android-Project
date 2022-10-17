package com.example.mymarketplaceapp.models;

import java.util.List;

public class Cart {
    private User user;
    private List<Item> item;
    private int totalCost;

    public Cart(User user, List<Item> item, int totalCost) {
        this.user = user;
        this.item = item;
        this.totalCost = totalCost;
    }

    public User getCartUser() {
        return user;
    }

    public List<Item> getCartItems() {
        return item;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }
}
