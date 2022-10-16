package com.example.mymarketplaceapp.models;

import android.content.Context;

import com.example.mymarketplaceapp.Utils.ContextUtil;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Item Data Access Object
 *
 * @author u7366711 Yuxuan Zhao
 */
public class ItemDao {
    private static ItemDao instance;

    private ItemDao() {
    }

    public static ItemDao getInstance() {
        if (instance == null)
            instance = new ItemDao();

        return instance;
    }

    public List<Item> getAllItems() {
        Context context = ContextUtil.getInstance();
        List<Item> itemList = new ArrayList<>();
        Item[] itemArray;

        InputStream inputStream;

        try {
            inputStream = context.getAssets().open("item.json");

            Gson gson = new Gson();
            itemArray = gson.fromJson(new InputStreamReader(inputStream), Item[].class);
            itemList = Arrays.asList(itemArray);
        } catch (IOException e) {

        }

        return itemList;
    }

    public List<Item> getCategoryItems(Category category) {
        List<Item> itemList = getAllItems();
        for(Item item : itemList)
            if(!item.getCategory().equals(category))
                itemList.remove(item);

        return itemList;
    }
}
