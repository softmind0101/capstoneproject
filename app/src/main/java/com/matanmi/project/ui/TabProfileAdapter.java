package com.matanmi.project.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.matanmi.project.ui.profile.ChangePicture;
import com.matanmi.project.ui.profile.Edit;
import com.matanmi.project.ui.profile.View;

public class TabProfileAdapter extends FragmentPagerAdapter {

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