package com.matanmi.project.controller;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.matanmi.project.R;
import com.matanmi.project.model.Profile;

import java.util.List;

/*
 * Controller  : AdminPatientDataAdapter.java
 * Date        : 2016
 * Version     : 1.00
 * Author      : Matanmi Falana
 * Copyright (c) 2016
 */

public class AdminPatientDataAdapter extends RecyclerView.Adapter<AdminPatientDataAdapter.ViewHolder> {
    String gender;
    Profile patientRecord = null;
    private List<Profile> patientRecords;

    public AdminPatientDataAdapter(List<Profile> patientRecords) {
        this.patientRecords = patientRecords;
    }

    @Override
    public AdminPatientDataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.admin_patient_card, viewGroup, false);
        return new ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txt_admin_patient_sn, txt_admin_patient_username, txt_admin_patient_age, txt_admin_patient_gender;
        public ViewHolder(View view) {
            super(view);
            txt_admin_patient_sn = (TextView)view.findViewById(R.id.txt_admin_patient_sn);
            txt_admin_patient_username = (TextView)view.findViewById(R.id.txt_admin_patient_username);
            txt_admin_patient_age = (TextView)view.findViewById(R.id.txt_admin_patient_age);
            txt_admin_patient_gender = (TextView)view.findViewById(R.id.txt_admin_patient_gender);
        }
    }

    @Override
    public int getItemCount() {
        return patientRecords.size();
    }

    @Override
    public void onBindViewHolder(AdminPatientDataAdapter.ViewHolder viewHolder, int i) {
        if(patientRecords != null) {
            patientRecord = patientRecords.get(i);
            viewHolder.txt_admin_patient_sn.setText(String.valueOf(++i));
            viewHolder.txt_admin_patient_username.setText(" ".concat(patientRecord.name));
            viewHolder.txt_admin_patient_age.setText(String.valueOf(patientRecord.age));
            if(patientRecord.gender.equals("M")){
                gender = "Male";
            } else if(patientRecord.gender.equals("F")){
                gender = "Female";
            }
            viewHolder.txt_admin_patient_gender.setText(" ".concat(gender));
        } else {
            viewHolder.txt_admin_patient_sn.setText("0");
            viewHolder.txt_admin_patient_username.setText(" ");
            viewHolder.txt_admin_patient_age.setText("0");
            viewHolder.txt_admin_patient_gender.setText(" ");
        }
    }
}
