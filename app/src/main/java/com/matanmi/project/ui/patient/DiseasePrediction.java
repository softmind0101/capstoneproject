package com.matanmi.project.ui.patient;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.matanmi.project.R;
import com.matanmi.project.util.SessionManager;

/*
 * UI Patient  : DiseasePrediction.java
 * Date        : 2016
 * Version     : 1.00
 * Author      : Matanmi Falana
 * Copyright (c) 2016
 */

public class DiseasePrediction extends Fragment {
    SessionManager session;

    public DiseasePrediction() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        session = new SessionManager(getActivity());
        session.checkLogin();
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.disease_prediction, container, false);
    }
}
