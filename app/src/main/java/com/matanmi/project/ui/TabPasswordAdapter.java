package com.matanmi.project.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.matanmi.project.ui.password.Change;

/*
 * UI          : TabPasswordAdapter.java
 * Date        : 2016
 * Version     : 1.00
 * Author      : Matanmi Falana
 * Copyright (c) 2016
 */

public class TabPasswordAdapter extends FragmentStatePagerAdapter {

    int tabCount;

    public TabPasswordAdapter(FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.tabCount = numberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                Change change = new Change();
                return change;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }

}