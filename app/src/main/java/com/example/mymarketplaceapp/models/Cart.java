package com.example.mymarketplaceapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Cart implements Parcelable {
    private User user;
    private List<Item> item;
    private double totalCost;

    public Cart(User user, List<Item> item, double totalCost) {
        this.user = user;
        this.item = item;
        this.totalCost = totalCost;
    }

    protected Cart(Parcel in) {
        totalCost = in.readInt();
    }

    public static final Creator<Cart> CREATOR = new Creator<Cart>() {
        @Override
        public Cart createFromParcel(Parcel in) {
            return new Cart(in);
        }

        @Override
        public Cart[] newArray(int size) {
            return new Cart[size];
        }
    };

    public User getCartUser() {
        return user;
    }

    public List<Item> getCartItems() {
        return item;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(totalCost);
    }
}
