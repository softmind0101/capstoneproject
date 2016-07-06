package com.matanmi.project.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.matanmi.project.ui.doctor.Details;
import com.matanmi.project.ui.doctor.PreviousDetails;

/*
 * UI          : TabDoctorAdapter.java
 * Date        : 2016
 * Version     : 1.00
 * Author      : Matanmi Falana
 * Copyright (c) 2016
 */

public class TabDoctorAdapter extends FragmentStatePagerAdapter {

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