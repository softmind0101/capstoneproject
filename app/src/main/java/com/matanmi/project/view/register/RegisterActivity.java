package com.matanmi.project.view.register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.matanmi.project.R;
import com.matanmi.project.model.Profile;
import com.matanmi.project.model.Role;
import com.matanmi.project.util.AbstractActivity;
import com.matanmi.project.util.AlertDialogManager;
import com.matanmi.project.util.Utilities;
import com.matanmi.project.view.MainActivity;
import com.matanmi.project.view.login.LoginActivity;

public class RegisterActivity extends AbstractActivity {
    String username, email, mobile, password, verifyPassword;
    String gender, role;
    ImageButton imgButton;
    Button registerButton;
    Spinner genderSpinner, roleSpinner;
    Intent intentInstance = null;
    private static final String[] genderArray = {"Male", "Female"};
    // Alert Dialog Manager
    AlertDialogManager alert = new AlertDialogManager();
    private Profile profile = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        genderSpinner = (Spinner) findViewById(R.id.gender_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(RegisterActivity.this,
                android.R.layout.simple_spinner_item, genderArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(adapter);
        genderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView,
                                       View selectedItemView, int position, long id) {
                gender = String.valueOf(genderSpinner.getSelectedItem());
                switch (gender) {
                    case "Male":
                        gender = "M";
                        break;
                    case "Female":
                        gender = "F";
                        break;
                    case "":
                        gender = "M";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

        roleSpinner = (Spinner) findViewById(R.id.role_spinner);
        // Loading spinner data from database
        loadSpinnerData();
        roleSpinner.setSelection(2);
        roleSpinner.setEnabled(false);
        // Spinner click listener
        roleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // On selecting a spinner item
                role = parent.getItemAtPosition(position).toString();
                switch (role) {
                    case "Patient":
                        role = "P";
                        break;
                    case "Doctor":
                        role = "D";
                        break;
                    case "Administrator":
                        role = "A";
                        break;
                    case "":
                        role = "P";
                        break;
                }
                // Showing selected spinner item
                //Toast.makeText(parent.getContext(), "You selected: " + label, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });

        registerButton = (Button) findViewById(R.id.register_button);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = ((EditText) findViewById(R.id.entered_username)).getText().toString();
                email = ((EditText) findViewById(R.id.entered_email)).getText().toString();
                mobile = ((EditText) findViewById(R.id.entered_mobile)).getText().toString();
                password = ((EditText) findViewById(R.id.entered_password)).getText().toString();
                verifyPassword = ((EditText) findViewById(R.id.entered_verify_Password)).getText().toString();

                if (username.isEmpty()) {
                    alert.showAlertDialog(RegisterActivity.this, "Data Validation", "Username cannot be empty.", false);
                } else if (email.isEmpty()) {
                    alert.showAlertDialog(RegisterActivity.this, "Data Validation", "Email cannot be empt.y", false);
                } else if (Utilities.isNotFormattedEmail(email)) {
                    alert.showAlertDialog(RegisterActivity.this, "Data Validation", "Email not formatted.", false);
                } else if (mobile.isEmpty()) {
                    alert.showAlertDialog(RegisterActivity.this, "Data Validation", "Mobile cannot be empty.", false);
                } else if (password.isEmpty()) {
                    alert.showAlertDialog(RegisterActivity.this, "Data Validation", "Password cannot be empty.", false);
                } else if (password.length() < 4) {
                    alert.showAlertDialog(RegisterActivity.this, "Data Validation", "Password length must be greater than 3.", false);
                } else if (!(password.equals(verifyPassword))) {
                    alert.showAlertDialog(RegisterActivity.this, "Data Validation", "Password and verify password must be same.", false);
                } else {
                    profile = new Profile();
                    profile.name = username;
                    profile.email = email;
                    profile.mobile = mobile;
                    profile.age = 0;
                    profile.gender = gender;
                    profile.role = role;
                    profile.password = password;
                    if (!(new Profile().isProfileExist(profile))){
                        if (new Profile().insertProfile(profile)) {
                            intentInstance = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivityForResult(intentInstance, 500);
                            finish();
                        } else {
                            alert.showAlertDialog(RegisterActivity.this, "Registration Error", "There is problem with registration ...", false);
                        }
                    } else {
                        alert.showAlertDialog(RegisterActivity.this, "Record Checking", "The Patient record is existing.", false);
                    }
                }
            }
        });
    }

    public void back(View view) {
        imgButton = (ImageButton) findViewById(R.id.imageButton);
        intentInstance = new Intent(RegisterActivity.this, MainActivity.class);
        startActivity(intentInstance);
        // close this activity
        finish();
    }

    /**
     * Function to load the spinner data from SQLite database
     * */
    private void loadSpinnerData() {
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, new Role().getRoleList());
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        roleSpinner.setAdapter(dataAdapter);
    }
}
