package com.matanmi.project.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.matanmi.project.ui.profile.ChangePicture;
import com.matanmi.project.ui.profile.Edit;
import com.matanmi.project.ui.profile.View;

/*
 * UI          : TabProfileAdapter.java
 * Date        : 2016
 * Version     : 1.00
 * Author      : Matanmi Falana
 * Copyright (c) 2016
 */

public class TabProfileAdapter extends FragmentStatePagerAdapter {

    int tabCount;

    public TabProfileAdapter(FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.tabCount = numberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                View info = new View();
                return info;
            case 1:
                Edit edit = new Edit();
                return edit;
            case 2:
                ChangePicture changePicture = new ChangePicture();
                return changePicture;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }

}