package com.example.mymarketplaceapp.models;

import java.util.List;

/**
 * No Session State (user has not signed in)
 *
 * @author u7366711 Yuxuan Zhao
 */
public class NoSessionState extends UserState {

    public NoSessionState(UserSession userSession) {
        super(userSession);
    }

    /**
     * Verify username and password
     *
     * @param username
     * @param password
     * @return User
     */
    @Override
    public User login(String username, String password) {
        UserDao userDao = UserDao.getInstance();
        List<User> userList = userDao.getAllUsers();

        for (User user : userList)
            if (user.getUsername().equals(username) && user.getPassword().equals(password))
                return user;

        return null;
    }

    @Override
    public boolean logout() {
        return false;
    }
}
