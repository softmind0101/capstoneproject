package com.matanmi.project.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.matanmi.project.ui.patient.Details;
import com.matanmi.project.ui.patient.DiseasePrediction;
import com.matanmi.project.ui.patient.Feedback;
import com.matanmi.project.ui.patient.SearchDoctor;

/*
 * UI          : TabPatientAdapter.java
 * Date        : 2016
 * Version     : 1.00
 * Author      : Matanmi Falana
 * Copyright (c) 2016
 */

public class TabPatientAdapter extends FragmentStatePagerAdapter {

    int tabCount;

    public TabPatientAdapter(FragmentManager fm, int numberOfTabs) {
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
                DiseasePrediction prediction = new DiseasePrediction();
                return prediction;
            case 2:
                SearchDoctor searchDoctor = new SearchDoctor();
                return searchDoctor;
            case 3:
                Feedback feedback = new Feedback();
                return feedback;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }

}