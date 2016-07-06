package com.matanmi.project.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.matanmi.project.ui.admin.Disease;
import com.matanmi.project.ui.admin.Doctor;
import com.matanmi.project.ui.admin.Feedback;
import com.matanmi.project.ui.admin.Patient;

public class TabAdminAdapter extends FragmentPagerAdapter {

    int tabCount;

    public TabAdminAdapter(FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.tabCount = numberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                Patient Patient = new Patient();
                return Patient;
            case 1:
                Doctor Doctor = new Doctor();
                return Doctor;
            case 2:
                Disease Disease = new Disease();
                return Disease;
            case 3:
                Feedback Feedback = new Feedback();
                return Feedback;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }

}