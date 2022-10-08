package com.example.mymarketplaceapp.Utils;

import android.app.Application;

import com.example.mymarketplaceapp.models.UserDao;

/**
 * Get Context in models
 *
 * @author u7366711 Yuxuan Zhao
 */
public class ContextUtil extends Application {
    private static ContextUtil instance;

    public static ContextUtil getInstance() {
        if (instance == null)
            instance = new ContextUtil();

        return instance;
    }

    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
