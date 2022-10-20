package com.example.mymarketplaceapp.utils;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.mymarketplaceapp.models.Category;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Parsed search query
 * <p>
 * Example:
 * "uPhone #ELECTRONICS @JB Wi-Fi >1024 <2048 ^bullet-proof"
 * itemName = "uPhone" (contain)
 * Category = ELECTRONICS
 * username (of seller) = "JB Wi-Fi"
 * MinPrice = 1024.00
 * MaxPrice = 2048.00
 * Description = "bullet-proof" (contain)
 *
 * @author u7366711 Yuxuan Zhao
 */
public class Query implements Parcelable {

    private String itemName;
    private List<Token> filter; // filter is the list of criteria

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
        for (Token token : filter)
            if (token.getType().equals(Token.Type.CATEGORY))
                return Category.valueOf(token.getToken().toUpperCase());

        return null;
    }

    public String getUsername() {
        for (Token token : filter)
            if (token.getType().equals(Token.Type.USERNAME))
                return token.getToken().toLowerCase();

        return null;
    }

    public double getMinPrice() {
        for (Token token : filter)
            if (token.getType().equals(Token.Type.MIN_PRICE))
                return Double.valueOf(token.getToken());

        return 0;
    }

    public double getMaxPrice() {
        for (Token token : filter)
            if (token.getType().equals(Token.Type.MAX_PRICE))
                return Double.valueOf(token.getToken());

        return Double.MAX_VALUE;
    }

    public String getDescription() {
        for (Token token : filter)
            if (token.getType().equals(Token.Type.DESCRIPTION))
                return token.getToken().toLowerCase();

        return null;
    }

    public String checkQuery() {
        if (itemName == "")
            return "Invalid query";

        int[] counter = {0, 0, 0, 0, 0, 0, 0};

        for (Token token : filter) {
            switch (token.getType()) {
                case CATEGORY:
                    if (!Arrays.asList(Stream.of(Token.Type.values()).map(Enum::name).toArray(String[]::new)).contains(token.getToken().toUpperCase()))
                        return "Invalid category";
                    counter[Token.Type.CATEGORY.ordinal()]++;
                    break;
                case USERNAME:
                    counter[Token.Type.USERNAME.ordinal()]++;
                    break;
                case MIN_PRICE:
                    try {
                        double price = Double.parseDouble(token.getToken());
                    } catch (NullPointerException nullPointerException) {
                        return "Invalid minimum price";
                    } catch (NumberFormatException numberFormatException) {
                        return "Invalid minimum price";
                    }
                    counter[Token.Type.MIN_PRICE.ordinal()]++;
                    break;
                case MAX_PRICE:
                    try {
                        double price = Double.parseDouble(token.getToken());
                    } catch (NullPointerException nullPointerException) {
                        return "Invalid maximum price";
                    } catch (NumberFormatException numberFormatException) {
                        return "Invalid maximum price";
                    }
                    counter[Token.Type.MAX_PRICE.ordinal()]++;
                    break;
                case DESCRIPTION:
                    counter[Token.Type.DESCRIPTION.ordinal()]++;
                    break;
                default:
                    return "";
            }

        }

        for (int count : counter)
            if (count > 1)
                return "Duplicate filter criteria";

        return "";
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
