package com.example.mymarketplaceapp.models;

/**
 * Get Cart Item in User
 * @author u7326123 Rita Zhou
 */
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
