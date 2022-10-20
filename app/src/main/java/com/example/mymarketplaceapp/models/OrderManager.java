package com.example.mymarketplaceapp.models;

import android.content.ClipData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Order Manager Class
 * Create and store orders
 *
 * @author u7366711 Yuxuan Zhao
 */
public class OrderManager {
    private static OrderManager instance;
    private static List<Order> orderList;

    public static OrderManager getInstance() {
        if (instance == null) {
            instance = new OrderManager();
            orderList = new ArrayList<>();
        }

        return instance;
    }

    /**
     * Create new orders
     * Example:
     * If A bought X from B and Y from C in an order, then three orders will be created:
     * - Order 1: A bought X from B and Y from C
     * - Order 2: B sold X to A
     * - Order 3: C sold Y to A
     *
     * @param buyerId
     * @param cartItemList
     */
    public void createNewOrder(int buyerId, List<CartItem> cartItemList) {
        List<CartItem> copy = new ArrayList<>();
        for( CartItem cartItem : cartItemList){
            copy.add(cartItem);
        }

        // create new buy order for buyer
        orderList.add(new Order(orderList.size(), buyerId, copy, Order.Type.BUY));

        // create new sell orders for seller(s)
        Set<Integer> sellerIdList = new HashSet<>();
        ItemDao itemDao = ItemDao.getInstance();
        // search sellers
        for (CartItem cartItem : cartItemList) {
            sellerIdList.add(itemDao.getItem(cartItem.getCartItemId()).getSellerUid());
        }
        // create new sell orders
        for (Integer sellerId : sellerIdList) {
            List<CartItem> soldItemList = new ArrayList<>();
            for (CartItem cartItem : cartItemList) {
                if (sellerId.equals(itemDao.getItem(cartItem.getCartItemId()).getSellerUid())) {
                    soldItemList.add(cartItem);
                }
            }
            orderList.add(new Order(orderList.size(), sellerId, soldItemList, Order.Type.SELL, buyerId));
        }

    }

    public List<Order> getOrderList() {
        return orderList;
    }

    /**
     *
     * @param userId
     * @return order list selected by userId
     */
    public List<Order> getOrderList(int userId) {
        List<Order> selectedOrderList = new ArrayList<>();

        for(Order order : orderList){
            if(order.getUserId()==userId){
                selectedOrderList.add(order);
            }
        }

        return selectedOrderList;
    }
}
