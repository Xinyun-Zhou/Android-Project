package com.example.mymarketplaceapp.models;


/**
 * Session State (user has signed in)
 *
 * @author u7366711 Yuxuan Zhao, u7326123 Rita Zhou
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
        return true;
    }
}
