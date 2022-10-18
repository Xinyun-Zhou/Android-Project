package com.example.mymarketplaceapp.utils;

import java.util.ArrayList;
import java.util.List;

public class Query {

    private String itemName;
    private List<Token> filter;

    public Query(String itemName) {
        this.itemName = itemName;
        filter = new ArrayList<>();
    }

    public Query(String itemName, List<Token> filter) {
        this.itemName = itemName;
        this.filter = filter;
    }

    public String getItemName() {
        return itemName;
    }

    public List<Token> getFilter() {
        return filter;
    }
}
