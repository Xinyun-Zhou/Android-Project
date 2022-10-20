package com.example.mymarketplaceapp.models;

import android.content.Context;

import com.example.mymarketplaceapp.utils.AVLTree;
import com.example.mymarketplaceapp.utils.ContextUtil;
import com.example.mymarketplaceapp.utils.Query;
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

    public AVLTree<Item> getAllItemsAVL() {
        Context context = ContextUtil.getInstance();
        Item[] itemArray;
        AVLTree<Item> itemAVLTree;

        InputStream inputStream;

        try {
            inputStream = context.getAssets().open("item.json");

            Gson gson = new Gson();
            itemArray = gson.fromJson(new InputStreamReader(inputStream), Item[].class);

            if (itemArray.length > 0) {
                itemAVLTree = new AVLTree<>(itemArray[0]);

                for (int i = 1; i < itemArray.length; i++)
                    itemAVLTree = itemAVLTree.insert(itemArray[i]);

                return itemAVLTree;
            }

        } catch (IOException e) {

        }

        return null;
    }

    public List<Item> getCategoryItems(Category category) {
        List<Item> itemList = getAllItems();
        List<Item> selectedItemList = new ArrayList<>();

        for (Item item : itemList)
            if (item.getCategory().equals(category))
                selectedItemList.add(item);

        return selectedItemList;
    }

    private List<Item> selectByCategory(List<Item> itemList, Category category) {
        List<Item> selectedItemList = new ArrayList<>();

        for (Item item : itemList)
            if (item.getCategory().equals(category))
                selectedItemList.add(item);

        return selectedItemList;
    }

    private List<Item> selectByUsername(List<Item> itemList, String username) {
        List<Item> selectedItemList = new ArrayList<>();

        UserDao userDao = UserDao.getInstance();
        User user = userDao.searchUser(username);

        if (user == null)
            return selectedItemList;

        for (Item item : itemList)
            if (item.getSellerUid() == user.getUid())
                selectedItemList.add(item);

        return selectedItemList;
    }

    private List<Item> selectByMinPrice(List<Item> itemList, double minPrice) {
        List<Item> selectedItemList = new ArrayList<>();

        for (Item item : itemList)
            if (item.getPrice() >= minPrice)
                selectedItemList.add(item);

        return selectedItemList;
    }

    private List<Item> selectByMaxPrice(List<Item> itemList, double maxPrice) {
        List<Item> selectedItemList = new ArrayList<>();

        for (Item item : itemList)
            if (item.getPrice() <= maxPrice)
                selectedItemList.add(item);

        return selectedItemList;
    }

    private List<Item> selectByDescription(List<Item> itemList, String description) {
        List<Item> selectedItemList = new ArrayList<>();

        for (Item item : itemList)
            if (item.getDescription().toLowerCase().contains(description.toLowerCase()))
                selectedItemList.add(item);

        return selectedItemList;
    }

    public List<Item> search(Query query) {
        List<Item> fullItemList = getAllItems();
        List<Item> selectedItemList = new ArrayList<>();


        for (Item item : fullItemList)
            if (item.getName().toLowerCase().contains(query.getItemName().toLowerCase()))
                selectedItemList.add(item);

        if (query.getCategory() != null)
            selectedItemList = selectByCategory(selectedItemList, query.getCategory());

        if (query.getUsername() != null)
            selectedItemList = selectByUsername(selectedItemList, query.getUsername());

        if (query.getMinPrice() > 0)
            selectedItemList = selectByMinPrice(selectedItemList, query.getMinPrice());

        if (query.getMaxPrice() < Double.MAX_VALUE)
            selectedItemList = selectByMaxPrice(selectedItemList, query.getMaxPrice());

        if (query.getDescription() != null)
            selectedItemList = selectByDescription(selectedItemList, query.getDescription());

        return selectedItemList;
    }
}