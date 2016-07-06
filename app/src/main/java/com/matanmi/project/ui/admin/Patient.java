package com.matanmi.project.ui.admin;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.matanmi.project.R;
import com.matanmi.project.controller.AdminPatientDataAdapter;
import com.matanmi.project.model.Profile;
import com.matanmi.project.util.SessionManager;

import java.util.List;

/*
 * UI Admin    : Patient.java
 * Date        : 2016
 * Version     : 1.00
 * Author      : Matanmi Falana
 * Copyright (c) 2016
 */

public class Patient extends Fragment {
    View rootView;
    private List<Profile> patientRecords;
    SessionManager session;

    public Patient() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        session = new SessionManager(getActivity());
        session.checkLogin();
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.admin_patient, container, false);
        initViews();
        return rootView;
    }

    private void initViews(){
        RecyclerView recyclerView = (RecyclerView)rootView.findViewById(R.id.admin_patient_card_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        patientRecords = new Profile().getPatientProfileRecords();
        RecyclerView.Adapter adapter = new AdminPatientDataAdapter(patientRecords);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            GestureDetector gestureDetector = new GestureDetector(getActivity(), new GestureDetector.SimpleOnGestureListener() {

                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

            });

            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

                View child = rv.findChildViewUnder(e.getX(), e.getY());
                if (child != null && gestureDetector.onTouchEvent(e)) {
                    int position = rv.getChildAdapterPosition(child);
                    //Toast.makeText(getActivity(), patientRecords.get(position), Toast.LENGTH_SHORT).show();
                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
    }
}
