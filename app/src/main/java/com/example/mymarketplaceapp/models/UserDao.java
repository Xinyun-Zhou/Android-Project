package com.example.mymarketplaceapp.models;

import android.content.Context;

import com.example.mymarketplaceapp.Utils.ContextUtil;
import com.example.mymarketplaceapp.utils.AVLTree;
import com.example.mymarketplaceapp.utils.Tree;
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

    public AVLTree<User> getAllUsersAVL() {
        Context context = ContextUtil.getInstance();
        User[] userArray;
        AVLTree<User> userAVLTree;

        InputStream inputStream;

        try {
            inputStream = context.getAssets().open("user.json");

            Gson gson = new Gson();
            userArray = gson.fromJson(new InputStreamReader(inputStream), User[].class);

            if (userArray.length > 0) {
                userAVLTree = new AVLTree<>(userArray[0]);

                for (int i = 1; i < userArray.length; i++)
                    userAVLTree = userAVLTree.insert(userArray[i]);

                return userAVLTree;
            }

        } catch (IOException e) {

        }

        return null;
    }

    public User searchUser(String username) {
        AVLTree<User> userAVLTree = getAllUsersAVL();
        Tree<User> result = userAVLTree.find(new User(username));
        if (result != null)
            return result.value;
        else
            return null;
    }
}
