package com.example.mymarketplaceapp.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * User Session
 * User Session defaults to NoSessionState and changes to SessionState after successful login
 *
 * @author u7366711 Yuxuan Zhao
 */
public class UserSession implements Parcelable {
    UserState userState;
    User user;

    public UserSession() {
        UserState defaultState = new NoSessionState(this);
        changeState(defaultState);
        user = null;
    }

    public void changeState(UserState state) {
        this.userState = state;
    }

    public boolean login(String username, String password) {
        user = userState.login(username, password);
        if (user != null)
            changeState(new SessionState(this));

        return user != null ? true : false;
    }

    public boolean logout(){
        if (userState.logout()) {
            user = new User();
            changeState(new NoSessionState(this));
        }
        return true;
    }

    public UserState getUserState() {
        return userState;
    }

    public User getUser() {
        return user;
    }

    protected UserSession(Parcel in) {
    }

    public static final Creator<UserSession> CREATOR = new Creator<UserSession>() {
        @Override
        public UserSession createFromParcel(Parcel in) {
            return new UserSession(in);
        }

        @Override
        public UserSession[] newArray(int size) {
            return new UserSession[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
