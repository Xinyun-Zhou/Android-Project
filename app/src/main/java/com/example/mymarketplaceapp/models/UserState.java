package com.example.mymarketplaceapp.models;

/**
 * User State
 * - No Session State (Default)
 * - Session State
 *
 * @author u7366711 Yuxuan Zhao
 */
public abstract class UserState {

    protected UserSession userSession;

    public UserState(UserSession userSession) {
        this.userSession = userSession;
    }

    public abstract User login(String username, String password);

    public abstract boolean logout();
}
