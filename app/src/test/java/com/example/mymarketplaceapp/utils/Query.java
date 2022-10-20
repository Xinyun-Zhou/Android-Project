package com.example.mymarketplaceapp.utils;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.mymarketplaceapp.models.Category;

import java.util.ArrayList;
import java.util.List;

public class Query implements Parcelable {

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

    protected Query(Parcel in) {
        itemName = in.readString();
    }

    public static final Creator<Query> CREATOR = new Creator<Query>() {
        @Override
        public Query createFromParcel(Parcel in) {
            return new Query(in);
        }

        @Override
        public Query[] newArray(int size) {
            return new Query[size];
        }
    };

    public String getItemName() {
        return itemName;
    }

    public boolean hasFilter() {
        return !filter.isEmpty();
    }

    public Category getCategory() {
        for(Token token : filter)
            if(token.getType().equals(Token.Type.CATEGORY))
                return Category.valueOf(token.getToken().toUpperCase());

        return null;
    }

    public String getUsername() {
        for(Token token : filter)
            if(token.getType().equals(Token.Type.USERNAME))
                return token.getToken().toLowerCase();

        return null;
    }

    public double getMinPrice() {
        for(Token token : filter)
            if(token.getType().equals(Token.Type.MIN_PRICE))
                return Double.valueOf(token.getToken());

        return 0;
    }

    public double getMaxPrice() {
        for(Token token : filter)
            if(token.getType().equals(Token.Type.MAX_PRICE))
                return Double.valueOf(token.getToken());

        return Double.MAX_VALUE;
    }

    public String getDescription() {
        for(Token token : filter)
            if(token.getType().equals(Token.Type.DESCRIPTION))
                return token.getToken().toLowerCase();

        return null;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(itemName);
    }
}
