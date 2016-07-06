package com.matanmi.project.ui.profile;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.matanmi.project.R;
import com.matanmi.project.util.SessionManager;

/*
 * UI Profile  : ChangePicture.java
 * Date        : 2016
 * Version     : 1.00
 * Author      : Matanmi Falana
 * Copyright (c) 2016
 */

public class ChangePicture extends Fragment {
    SessionManager session;

    public ChangePicture() {
        // Required empty public constructor
    }

    @Override
    public android.view.View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        session = new SessionManager(getActivity());
        session.checkLogin();
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.profile_change_picture, container, false);
    }
}
