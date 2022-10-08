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
 * User Data Access Object
 *
 * @author u7366711 Yuxuan Zhao
 */
public class UserDao implements UserDaoInterface {
    private static UserDao instance;

    private UserDao() {
    }

    public static UserDao getInstance() {
        if (instance == null)
            instance = new UserDao();

        return instance;
    }

    /**
     * Read and parse user.json
     *
     * @return Arraylist of all users
     */
    public List<User> getAllUsers() {
        Context context = ContextUtil.getInstance();
        List<User> userList = new ArrayList<>();
        User[] userArray;

        InputStream inputStream;

        try {
            inputStream = context.getAssets().open("user.json");

            Gson gson = new Gson();
            userArray = gson.fromJson(new InputStreamReader(inputStream), User[].class);
            userList = Arrays.asList(userArray);
        } catch (IOException e) {

        }

        return userList;
    }
}
