package com.matanmi.project.util;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public abstract class AbstractActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }
}
