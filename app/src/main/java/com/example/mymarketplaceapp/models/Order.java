package com.example.mymarketplaceapp.models;

import java.util.List;

public class Order {
    public enum Type {BUY,SELL}

    private int orderId;
    private int userId;
    private List<CartItem> orderItemList;
    private Type orderType;
    private int buyerId;

    public Order(int orderId, int userId, List<CartItem> orderItemList, Type orderType){
        this.orderId = orderId;
        this.userId = userId;
        this.orderItemList = orderItemList;
        this.orderType = orderType;
    }

    public Order(int orderId, int userId, List<CartItem> orderItemList, Type orderType, int buyerId){
        this.orderId = orderId;
        this.userId = userId;
        this.orderItemList = orderItemList;
        this.orderType = orderType;
        this.buyerId = buyerId;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getUserId() {
        return userId;
    }

    public List<CartItem> getOrderItemList() {
        return orderItemList;
    }

    public Type getOrderType() {
        return orderType;
    }

    public double getOrderValue() {
        double value = 0;
        ItemDao itemDao = ItemDao.getInstance();
        for(CartItem cartItem : orderItemList)
            value+=cartItem.getCartItemQuantity()*itemDao.getItem(cartItem.getCartItemId()).getPrice();

        return value;
    }

    public String getUsername() {
        UserDao userDao = UserDao.getInstance();

        return userDao.getUsername(userId);
    }

    public String getBuyerName() {
        UserDao userDao = UserDao.getInstance();

        return userDao.getUsername(buyerId);
    }
}
