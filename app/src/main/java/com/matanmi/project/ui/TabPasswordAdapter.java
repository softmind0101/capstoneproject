package com.matanmi.project.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.matanmi.project.ui.password.Change;

public class TabPasswordAdapter extends FragmentPagerAdapter {

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