package com.matanmi.project.ui.admin;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.matanmi.project.R;
import com.matanmi.project.controller.AdminDoctorDataAdapter;
import com.matanmi.project.model.Profile;
import com.matanmi.project.model.Role;
import com.matanmi.project.util.AlertDialogManager;
import com.matanmi.project.util.SessionManager;
import com.matanmi.project.util.Utilities;

import java.util.List;

/*23
 * UI Admin    : Doctor.java
 * Date        : 2016
 * Version     : 1.00
 * Author      : Matanmi Falana
 * Copyright (c) 2016
 */

public class Doctor extends Fragment {
    View rootView;
    String username, email, mobile;
    String gender, role;
    Spinner genderSpinner, roleSpinner;
    Button listAdminAddDoctor;
    Button addAdminRegisterDoctor, addAdminListDoctor;
    Button viewAdminListDoctor, viewAdminViewDoctor, viewAdminEditDoctor;
    Button editAdminListDoctor, editAdminViewDoctor, editAdminEditDoctor;
    RelativeLayout adminDoctorList, adminDoctorAdd, adminDoctorView, adminDoctorEdit;
    private List<Profile> doctorRecords;
    private static final String[] genderArray = {"Male", "Female"};
    AlertDialogManager alert = new AlertDialogManager();
    private Profile profile = null;
    SessionManager session;
    int containerId;

    public Doctor() {
        username = email = mobile = "";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        session = new SessionManager(getActivity());
        session.checkLogin();
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.admin_doctor, container, false);
        adminDoctorList = (RelativeLayout) rootView.findViewById(R.id.admin_doctor_list);
        adminDoctorAdd = (RelativeLayout) rootView.findViewById(R.id.admin_doctor_add);
        adminDoctorView = (RelativeLayout) rootView.findViewById(R.id.admin_doctor_view);
        adminDoctorEdit = (RelativeLayout) rootView.findViewById(R.id.admin_doctor_edit);

        adminDoctorList.setVisibility(View.VISIBLE);
        adminDoctorAdd.setVisibility(View.INVISIBLE);
        adminDoctorView.setVisibility(View.INVISIBLE);
        adminDoctorEdit.setVisibility(View.INVISIBLE);
        containerId = container.getId();
        initViews();
        return rootView;
    }

    private void initViews(){
        RecyclerView recyclerView = (RecyclerView)rootView.findViewById(R.id.admin_doctor_card_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        doctorRecords = new Profile().getDoctorProfileRecords();
        RecyclerView.Adapter adapter = new AdminDoctorDataAdapter(doctorRecords);
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
                    Profile doctorRecord = new Profile().getProfileRecord((doctorRecords.get(position)).id);
                    EditText username = (EditText) rootView.findViewById(R.id.view_doctor_username);
                    EditText email = (EditText) rootView.findViewById(R.id.view_doctor_email);
                    EditText mobile = (EditText) rootView.findViewById(R.id.view_doctor_mobile);
                    genderSpinner = (Spinner) rootView.findViewById(R.id.view_doctor_gender_spinner);
                    roleSpinner = (Spinner) rootView.findViewById(R.id.view_doctor_role_spinner);

                    session.createStringSession("doctor", doctorRecord.email);
                    username.setText(doctorRecord.name);
                    email.setText(doctorRecord.email);
                    mobile.setText(doctorRecord.mobile);
                    spinnerGender();
                    spinnerRole();

                    username.setEnabled(false);
                    email.setEnabled(false);
                    mobile.setEnabled(false);
                    genderSpinner.setEnabled(false);
                    roleSpinner.setEnabled(false);

                    adminDoctorList.setVisibility(View.INVISIBLE);
                    adminDoctorAdd.setVisibility(View.INVISIBLE);
                    adminDoctorView.setVisibility(View.VISIBLE);
                    adminDoctorView.setEnabled(false);
                    adminDoctorEdit.setVisibility(View.INVISIBLE);
                    Toast.makeText(getActivity(), "Viewing " + doctorRecord.email + " Record ..... ", Toast.LENGTH_SHORT).show();
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

        listAdminAddDoctor = (Button) rootView.findViewById(R.id.list_admin_add_doctor);
        listAdminAddDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText username = (EditText) rootView.findViewById(R.id.add_doctor_username);
                EditText email = (EditText) rootView.findViewById(R.id.add_doctor_email);
                EditText mobile = (EditText) rootView.findViewById(R.id.add_doctor_mobile);
                genderSpinner = (Spinner) rootView.findViewById(R.id.add_doctor_gender_spinner);
                roleSpinner = (Spinner) rootView.findViewById(R.id.add_doctor_role_spinner);

                username.setText("");
                email.setText("");
                mobile.setText("");
                spinnerGender();
                spinnerRole();

                adminDoctorList.setVisibility(View.INVISIBLE);
                adminDoctorAdd.setVisibility(View.VISIBLE);
                adminDoctorView.setVisibility(View.INVISIBLE);
                adminDoctorEdit.setVisibility(View.INVISIBLE);
                Toast.makeText(getActivity(), " Adding New Record ..... ", Toast.LENGTH_SHORT).show();
            }
        });

        addAdminRegisterDoctor = (Button) rootView.findViewById(R.id.add_admin_register_doctor);
        addAdminRegisterDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = ((EditText) rootView.findViewById(R.id.add_doctor_username)).getText().toString();
                email = ((EditText) rootView.findViewById(R.id.add_doctor_email)).getText().toString();
                mobile = ((EditText) rootView.findViewById(R.id.add_doctor_mobile)).getText().toString();
                genderSpinner = (Spinner) rootView.findViewById(R.id.add_doctor_gender_spinner);
                roleSpinner = (Spinner) rootView.findViewById(R.id.add_doctor_role_spinner);
                spinnerGender();
                spinnerRole();

                if (username.isEmpty()) {
                    alert.showAlertDialog(getActivity(), "Data Validation", "Username cannot be empty.", false);
                } else if (email.isEmpty()) {
                    alert.showAlertDialog(getActivity(), "Data Validation", "Email cannot be empt.y", false);
                } else if (Utilities.isNotFormattedEmail(email)) {
                    alert.showAlertDialog(getActivity(), "Data Validation", "Email not formatted.", false);
                } else if (mobile.isEmpty()) {
                    alert.showAlertDialog(getActivity(), "Data Validation", "Mobile cannot be empty.", false);
                } else {
                    profile = new Profile();
                    profile.name = username;
                    profile.email = email;
                    profile.mobile = mobile;
                    profile.age = 0;
                    profile.gender = gender;
                    profile.role = role;
                    profile.password = "";
                    if (!(new Profile().isProfileExist(profile))) {
                        if (new Profile().insertProfile(profile)) {
                            Fragment frg = getFragmentManager().findFragmentById(containerId);
                            final FragmentTransaction ft = getFragmentManager().beginTransaction();
                            ft.detach(frg);
                            ft.attach(frg);
                            ft.commit();
                            initViews();
                            adminDoctorList.setVisibility(View.VISIBLE);
                            adminDoctorAdd.setVisibility(View.INVISIBLE);
                            adminDoctorView.setVisibility(View.INVISIBLE);
                            adminDoctorEdit.setVisibility(View.INVISIBLE);
                            Toast.makeText(getActivity(), "Doctor with " + email + "/" + mobile + " Record has been registered.", Toast.LENGTH_SHORT).show();
                        } else {
                            alert.showAlertDialog(getActivity(), "Doctor Registration Error", "There is problem with registration ...", false);
                            adminDoctorList.setVisibility(View.INVISIBLE);
                            adminDoctorAdd.setVisibility(View.VISIBLE);
                            adminDoctorView.setVisibility(View.INVISIBLE);
                            adminDoctorEdit.setVisibility(View.INVISIBLE);
                        }
                    } else {
                        Fragment frg = getFragmentManager().findFragmentById(containerId);
                        final FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.detach(frg);
                        ft.attach(frg);
                        ft.commit();
                        initViews();
                        adminDoctorList.setVisibility(View.VISIBLE);
                        adminDoctorAdd.setVisibility(View.INVISIBLE);
                        adminDoctorView.setVisibility(View.INVISIBLE);
                        adminDoctorEdit.setVisibility(View.INVISIBLE);
                        alert.showAlertDialog(getActivity(), "Record Checking", "The Doctor record already exist.", false);
                    }
                }
            }
        });

        addAdminListDoctor = (Button) rootView.findViewById(R.id.add_admin_list_doctor);
        addAdminListDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adminDoctorList.setVisibility(View.VISIBLE);
                adminDoctorAdd.setVisibility(View.INVISIBLE);
                adminDoctorView.setVisibility(View.INVISIBLE);
                adminDoctorEdit.setVisibility(View.INVISIBLE);
                Toast.makeText(getActivity(), " Listing Records ..... ", Toast.LENGTH_SHORT).show();
            }
        });

        viewAdminListDoctor = (Button) rootView.findViewById(R.id.view_admin_list_doctor);
        viewAdminListDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adminDoctorList.setVisibility(View.VISIBLE);
                adminDoctorAdd.setVisibility(View.INVISIBLE);
                adminDoctorView.setVisibility(View.INVISIBLE);
                adminDoctorEdit.setVisibility(View.INVISIBLE);
                Toast.makeText(getActivity(), "Listing Records ..... ", Toast.LENGTH_SHORT).show();
            }
        });

        viewAdminViewDoctor = (Button) rootView.findViewById(R.id.view_admin_view_doctor);
        viewAdminViewDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Profile doctorRecord = new Profile().getProfileRecord((session.getStringData("doctor")).get("doctor"));
                EditText username = (EditText) rootView.findViewById(R.id.view_doctor_username);
                EditText email = (EditText) rootView.findViewById(R.id.view_doctor_email);
                EditText mobile = (EditText) rootView.findViewById(R.id.view_doctor_mobile);
                genderSpinner = (Spinner) rootView.findViewById(R.id.view_doctor_gender_spinner);
                roleSpinner = (Spinner) rootView.findViewById(R.id.view_doctor_role_spinner);

                username.setText(doctorRecord.name);
                email.setText(doctorRecord.email);
                mobile.setText(doctorRecord.mobile);
                spinnerGender();
                spinnerRole();

                username.setEnabled(false);
                email.setEnabled(false);
                mobile.setEnabled(false);
                genderSpinner.setEnabled(false);
                roleSpinner.setEnabled(false);

                adminDoctorList.setVisibility(View.INVISIBLE);
                adminDoctorAdd.setVisibility(View.INVISIBLE);
                adminDoctorView.setVisibility(View.VISIBLE);
                adminDoctorView.setEnabled(false);
                adminDoctorEdit.setVisibility(View.INVISIBLE);
                Toast.makeText(getActivity(), "Viewing " + doctorRecord.email + " Record ..... ", Toast.LENGTH_SHORT).show();
            }
        });

        viewAdminEditDoctor = (Button) rootView.findViewById(R.id.view_admin_edit_doctor);
        viewAdminEditDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Profile doctorRecord = new Profile().getProfileRecord((session.getStringData("doctor")).get("doctor"));
                EditText username = (EditText) rootView.findViewById(R.id.view_doctor_username);
                EditText email = (EditText) rootView.findViewById(R.id.view_doctor_email);
                EditText mobile = (EditText) rootView.findViewById(R.id.view_doctor_mobile);
                genderSpinner = (Spinner) rootView.findViewById(R.id.view_doctor_gender_spinner);
                roleSpinner = (Spinner) rootView.findViewById(R.id.view_doctor_role_spinner);

                username.setText(doctorRecord.name);
                email.setText(doctorRecord.email);
                mobile.setText(doctorRecord.mobile);
                spinnerGender();
                spinnerRole();

                adminDoctorList.setVisibility(View.INVISIBLE);
                adminDoctorAdd.setVisibility(View.INVISIBLE);
                adminDoctorView.setVisibility(View.VISIBLE);
                adminDoctorEdit.setVisibility(View.INVISIBLE);
                Toast.makeText(getActivity(), "Editing " + doctorRecord.email + " Record ..... ", Toast.LENGTH_SHORT).show();
            }
        });

        editAdminListDoctor = (Button) rootView.findViewById(R.id.edit_admin_list_doctor);
        editAdminListDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adminDoctorList.setVisibility(View.VISIBLE);
                adminDoctorAdd.setVisibility(View.INVISIBLE);
                adminDoctorView.setVisibility(View.INVISIBLE);
                adminDoctorEdit.setVisibility(View.INVISIBLE);
                Toast.makeText(getActivity(), "Listing Records ..... ", Toast.LENGTH_SHORT).show();
            }
        });

        editAdminViewDoctor = (Button) rootView.findViewById(R.id.edit_admin_view_doctor);
        editAdminViewDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Profile doctorRecord = new Profile().getProfileRecord((session.getStringData("doctor")).get("doctor"));
                EditText username = (EditText) rootView.findViewById(R.id.view_doctor_username);
                EditText email = (EditText) rootView.findViewById(R.id.view_doctor_email);
                EditText mobile = (EditText) rootView.findViewById(R.id.view_doctor_mobile);
                genderSpinner = (Spinner) rootView.findViewById(R.id.view_doctor_gender_spinner);
                roleSpinner = (Spinner) rootView.findViewById(R.id.view_doctor_role_spinner);

                username.setText(doctorRecord.name);
                email.setText(doctorRecord.email);
                mobile.setText(doctorRecord.mobile);
                spinnerGender();
                spinnerRole();

                adminDoctorList.setVisibility(View.INVISIBLE);
                adminDoctorAdd.setVisibility(View.INVISIBLE);
                adminDoctorView.setVisibility(View.VISIBLE);
                adminDoctorEdit.setVisibility(View.INVISIBLE);
                Toast.makeText(getActivity(), "Viewing " + doctorRecord.email + " Record ..... ", Toast.LENGTH_SHORT).show();
            }
        });

        editAdminEditDoctor = (Button) rootView.findViewById(R.id.edit_admin_edit_doctor);
        editAdminEditDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Profile doctorRecord = new Profile().getProfileRecord((session.getStringData("doctor")).get("doctor"));
                EditText username = (EditText) rootView.findViewById(R.id.view_doctor_username);
                EditText email = (EditText) rootView.findViewById(R.id.view_doctor_email);
                EditText mobile = (EditText) rootView.findViewById(R.id.view_doctor_mobile);
                genderSpinner = (Spinner) rootView.findViewById(R.id.view_doctor_gender_spinner);
                roleSpinner = (Spinner) rootView.findViewById(R.id.view_doctor_role_spinner);

                username.setText(doctorRecord.name);
                email.setText(doctorRecord.email);
                mobile.setText(doctorRecord.mobile);
                spinnerGender();
                spinnerRole();

                adminDoctorList.setVisibility(View.INVISIBLE);
                adminDoctorAdd.setVisibility(View.INVISIBLE);
                adminDoctorView.setVisibility(View.INVISIBLE);
                adminDoctorEdit.setVisibility(View.VISIBLE);
                adminDoctorEdit.setEnabled(false);
                Toast.makeText(getActivity(), "Editing " + doctorRecord.email + " Record ..... ", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void spinnerGender(){
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
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
    }

    private void spinnerRole(){
        // Loading spinner data from database
        loadSpinnerData();
        roleSpinner.setSelection(1);
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
                        role = "D";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });
    }

    /**
     * Function to load the spinner data from database
     * */
    private void loadSpinnerData() {
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, new Role().getRoleList());
        // Drop down layout style
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        roleSpinner.setAdapter(dataAdapter);
    }
}
