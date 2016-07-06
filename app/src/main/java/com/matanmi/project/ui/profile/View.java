package com.matanmi.project.ui.profile;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.matanmi.project.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class View extends Fragment {


    public View() {
        // Required empty public constructor
    }


    @Override
    public android.view.View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.profile_view, container, false);
    }

}
