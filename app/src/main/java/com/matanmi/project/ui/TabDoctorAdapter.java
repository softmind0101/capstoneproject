package com.matanmi.project.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.matanmi.project.ui.doctor.PreviousDetails;
import com.matanmi.project.ui.doctor.Details;

public class TabDoctorAdapter extends FragmentPagerAdapter {

    int tabCount;

    public TabDoctorAdapter(FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.tabCount = numberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                Details details = new Details();
                return details;
            case 1:
                PreviousDetails previousDetails = new PreviousDetails();
                return previousDetails;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }

}