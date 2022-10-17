package com.example.mymarketplaceapp.models;

import java.util.Map;

public class CartItem {
    public int id;
    public int quantity;

    public CartItem(int id, int quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public int getCartItemId() {
        return id;
    }

    public int getCartItemQuantity() {
        return quantity;
    }

    public void setCartItemQuantity(int quantity) {
        this.quantity = quantity;
    }
}
