package com.matanmi.project.ui.password;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.matanmi.project.R;
import com.matanmi.project.util.SessionManager;

/*
 * UI Password : Change.java
 * Date        : 2016
 * Version     : 1.00
 * Author      : Matanmi Falana
 * Copyright (c) 2016
 */

public class Change extends Fragment {
    SessionManager session;

    public Change() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        session = new SessionManager(getActivity());
        session.checkLogin();
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.change_password, container, false);
    }
}
