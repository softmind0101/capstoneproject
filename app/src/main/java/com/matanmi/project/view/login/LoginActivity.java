package com.matanmi.project.view.login;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.matanmi.project.BaseActivity;
import com.matanmi.project.R;
import com.matanmi.project.database.DatabaseHelper;
import com.matanmi.project.model.Profile;
import com.matanmi.project.util.AbstractActivity;
import com.matanmi.project.util.AlertDialogManager;
import com.matanmi.project.util.SessionManager;
import com.matanmi.project.view.MainActivity;

public class LoginActivity extends AbstractActivity {

    EditText mobilEmail, password;
    ImageButton imgButton;
    Button loginButton;
    SQLiteDatabase db = null;
    Intent intentInstance = null;
    // Alert Dialog Manager
    AlertDialogManager alert = new AlertDialogManager();
    // Session Manager Class
    SessionManager session;
    Cursor cursor;
    private static com.matanmi.project.database.table.Profile profileTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Session Manager
        session = new SessionManager(getApplicationContext());

        mobilEmail = (EditText) findViewById(R.id.entered_mobile_email);
        password = (EditText) findViewById(R.id.entered_password);

        //show("User Login Status: " + session.isLoggedIn());

        loginButton = (Button) findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strMobilEmail = mobilEmail.getText().toString();
                String strPassword = password.getText().toString();
                if((strMobilEmail.isEmpty()) || (strMobilEmail.equals(""))) {
                    // Mobile Number OR Email cannot be empty
                    alert.showAlertDialog(LoginActivity.this, "Login failed..", "Mobile Number OR Email cannot be empty", false);
                } else if((strPassword.isEmpty()) || (strPassword.equals("")) || (strPassword.length() < 4)) {
                    // Password cannot be null OR less than 4 in length
                    alert.showAlertDialog(LoginActivity.this, "Login failed..", "Password cannot be null OR less than 4 in length", false);
                } else {
                    if(new Profile().isProfileExist(strMobilEmail)){
                        db = DatabaseHelper.getDatabase();
                        cursor = db.rawQuery(profileTable.LOGIN_CHECK, new String[] {strMobilEmail, strMobilEmail, strPassword});
                        cursor.moveToFirst();
                        if(cursor.getCount() > 0)
                        {
                            session.createLoginSession(new Profile().getProfileRecord(strMobilEmail));
                            intentInstance = new Intent(LoginActivity.this, BaseActivity.class);
                            startActivityForResult(intentInstance, 500);
                            finish();
                        } else {
                            show(" Wrong Password or Mobile number. ");
                        }
                    } else {
                        show(" You have not registered as a user. ");
                    }
                }
            }
        });
    }

    public void show(String str)
    {
        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG).show();
    }

    public void forgot(View view) {

    }

    public void back(View view) {
        imgButton = (ImageButton) findViewById(R.id.imageButton);
        intentInstance = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intentInstance);
        // close this activity
        finish();
    }
}
