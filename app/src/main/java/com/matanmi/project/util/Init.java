package com.matanmi.project.util;

import android.app.Application;
import android.content.Context;

/*
 * Util        : Init.java
 * Date        : 2016
 * Version     : 1.00
 * Author      : Matanmi Falana
 * Copyright (c) 2016
 */

public class Init extends Application {

    public static Context mContext;
    public static final String PACKAGE_NAME="com.matanmi.project";

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public static Context getAppContext() {
        return mContext;
    }
}

