package com.example.mymarketplaceapp.models;

import android.content.Context;

import com.example.mymarketplaceapp.utils.ContextUtil;
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
 * Read user data from json
 * Generate user list and user AVL tree
 * Search user based on username
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

    public String getUsername(int uid){
        List<User> userList = getAllUsers();

        for(User user : userList)
            if(uid==user.getUid())
                return user.getUsername();

        return null;
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

    /**
     * Read and parse user.json
     *
     * @return AVL Tree of all users
     */
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

    /**
     * Search user based on username
     *
     * @param username
     * @return instance of user with given username
     */
    public User searchUser(String username) {
        AVLTree<User> userAVLTree = getAllUsersAVL();
        Tree<User> result = userAVLTree.find(new User(username));
        if (result != null)
            return result.value;
        else
            return null;
    }

    public List<Integer> cartItemsId(String username){
        ArrayList<Integer> itemsId = new ArrayList<>();
        User currentUser = searchUser(username);
        for (CartItem cartItem : currentUser.getCart()){
            itemsId.add(cartItem.getCartItemId());
        }
        return itemsId;
    }
}
