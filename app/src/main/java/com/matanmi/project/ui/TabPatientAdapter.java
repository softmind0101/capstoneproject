package com.matanmi.project.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.matanmi.project.ui.patient.Details;
import com.matanmi.project.ui.patient.DiseasePrediction;
import com.matanmi.project.ui.patient.SearchDoctor;
import com.matanmi.project.ui.patient.Feedback;

public class TabPatientAdapter extends FragmentPagerAdapter {

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