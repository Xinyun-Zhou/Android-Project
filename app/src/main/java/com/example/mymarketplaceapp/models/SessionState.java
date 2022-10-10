package com.example.mymarketplaceapp.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Session State (user has signed in)
 *
 * @author u7366711 Yuxuan Zhao
 */
public class SessionState extends UserState {

    public SessionState(UserSession userSession) {
        super(userSession);
    }

    @Override
    public User login(String username, String password) {
        return null;
    }

    @Override
    public boolean logout() {
        return false;
    }
}
