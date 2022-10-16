package com.example.mymarketplaceapp.models;

/**
 * Item
 *
 * @author u7366711 Yuxuan Zhao
 */
public class Item {
    private int id;
    private String name;
    private int price;
    private int quantity;
    private int sellerUid;
    private Category category;
    private String description;

    public Item(int id, String name, int price, int quantity, int sellerUid, Category category, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.sellerUid = sellerUid;
        this.category = category;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getSellerUid() {
        return sellerUid;
    }

    public Category getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }
}
