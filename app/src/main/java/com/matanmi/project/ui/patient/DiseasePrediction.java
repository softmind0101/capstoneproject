package com.matanmi.project.ui.patient;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.matanmi.project.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class DiseasePrediction extends Fragment {


    public DiseasePrediction() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.disease_prediction, container, false);
    }

}