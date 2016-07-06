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
 * Controller  : AdminDoctorDataAdapter.java
 * Date        : 2016
 * Version     : 1.00
 * Author      : Matanmi Falana
 * Copyright (c) 2016
 */

public class AdminDoctorDataAdapter extends RecyclerView.Adapter<AdminDoctorDataAdapter.ViewHolder> {
    String gender;
    Profile doctorRecord = null;
    private List<Profile> doctorRecords;

    public AdminDoctorDataAdapter(List<Profile> doctorRecords) {
        this.doctorRecords = doctorRecords;
    }

    @Override
    public AdminDoctorDataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.admin_doctor_card, viewGroup, false);
        return new ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txt_admin_doctor_sn, txt_admin_doctor_username, txt_admin_doctor_age, txt_admin_doctor_gender;
        public ViewHolder(View view) {
            super(view);
            txt_admin_doctor_sn = (TextView)view.findViewById(R.id.txt_admin_doctor_sn);
            txt_admin_doctor_username = (TextView)view.findViewById(R.id.txt_admin_doctor_username);
            txt_admin_doctor_age = (TextView)view.findViewById(R.id.txt_admin_doctor_age);
            txt_admin_doctor_gender = (TextView)view.findViewById(R.id.txt_admin_doctor_gender);
        }
    }

    @Override
    public int getItemCount() {
        return doctorRecords.size();
    }

    @Override
    public void onBindViewHolder(AdminDoctorDataAdapter.ViewHolder viewHolder, int i) {
        if(doctorRecords != null) {
            doctorRecord = doctorRecords.get(i);
            viewHolder.txt_admin_doctor_sn.setText(String.valueOf(++i));
            viewHolder.txt_admin_doctor_username.setText(" ".concat(doctorRecord.name));
            viewHolder.txt_admin_doctor_age.setText(String.valueOf(doctorRecord.age));
            if(doctorRecord.gender.equals("M")){
                gender = "Male";
            } else if(doctorRecord.gender.equals("F")){
                gender = "Female";
            }
            viewHolder.txt_admin_doctor_gender.setText(" ".concat(gender));
        } else {
            viewHolder.txt_admin_doctor_sn.setText("0");
            viewHolder.txt_admin_doctor_username.setText(" ");
            viewHolder.txt_admin_doctor_age.setText("0");
            viewHolder.txt_admin_doctor_gender.setText(" ");
        }
    }
}
