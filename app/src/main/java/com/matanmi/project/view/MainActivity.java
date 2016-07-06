package com.matanmi.project.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.matanmi.project.R;
import com.matanmi.project.util.AbstractActivity;
import com.matanmi.project.view.login.LoginActivity;
import com.matanmi.project.view.register.RegisterActivity;

public class MainActivity extends AbstractActivity {
    Intent intentInstance = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void login_register(View view)
    {
        switch(view.getId())
        {
            case R.id.login:
                intentInstance = new Intent(this,LoginActivity.class);
                startActivityForResult(intentInstance, 500);
                break;
            case R.id.register:
                intentInstance=new Intent(this,RegisterActivity.class);
                startActivityForResult(intentInstance, 500);
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

    }
}
