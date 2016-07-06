package com.matanmi.project.util;

import android.app.Application;
import android.content.Context;

public class Init extends Application {

    public static Context mContext;
    public static final String PACKAGE_NAME="com.matanmi.capstoneproject";

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public static Context getAppContext() {
        return mContext;
    }
}

