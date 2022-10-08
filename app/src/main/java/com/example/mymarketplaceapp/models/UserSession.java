package com.example.mymarketplaceapp.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * User Session
 *
 * @author u7366711 Yuxuan Zhao
 */
public class UserSession implements Parcelable {
    UserState userState;
    int uid;

    public UserSession() {
        UserState defaultState = new NoSessionState(this);
        changeState(defaultState);
        uid = -1;
    }

    public void changeState(UserState state) {
        this.userState = state;
    }

    public boolean login(String username, String password) {
        uid = userState.login(username, password);

        return uid != -1 ? true : false;
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
