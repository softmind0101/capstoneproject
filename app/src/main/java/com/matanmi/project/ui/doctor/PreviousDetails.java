package com.matanmi.project.ui.doctor;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.matanmi.project.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class PreviousDetails extends Fragment {


    public PreviousDetails() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.doctor_patient_previous_details, container, false);
    }

}
