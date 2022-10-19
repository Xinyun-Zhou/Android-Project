package com.example.mymarketplaceapp.models;

/**
 * Item class
 * Sorting by product name in the AVL tree by default
 * Sorting by id for products with the same name
 *
 * @author u7366711 Yuxuan Zhao
 */
public class Item implements Comparable<Item> {
    private int id;
    private String name;
    private double price;
    private int quantity;
    private int sellerUid;
    private Category category;
    private String description;

    public Item(int id, String name, double price, int quantity, int sellerUid, Category category, String description) {
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

    public double getPrice() {
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

    @Override
    public int compareTo(Item o) {
        int strDifference = this.name.compareTo(o.name);
        if (strDifference < 0) {
            return -1;
        } else if (strDifference > 0) {
            return 1;
        } else {
            if (this.id < o.id) {
                return -1;
            } else if (this.id > o.id) {
                return 1;
            } else {
                return 0;
            }
        }
    }
}
