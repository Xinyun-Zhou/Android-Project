package com.example.mymarketplaceapp.models;

import java.util.List;

public class Order {
    public enum Type {BUY,SELL}

    private int orderId;
    private int userId;
    private List<CartItem> orderItemList;
    private Type orderType;

    public Order(int orderId, int userId, List<CartItem> orderItemList, Type orderType){
        this.orderId = orderId;
        this.userId = userId;
        this.orderItemList = orderItemList;
        this.orderType = orderType;
    }


}
