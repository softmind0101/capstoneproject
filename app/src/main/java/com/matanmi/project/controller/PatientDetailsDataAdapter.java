package com.matanmi.project.controller;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.matanmi.project.R;

import java.util.ArrayList;

/*
 * Controller  : PatientDetailsDataAdapter.java
 * Date        : 2016
 * Version     : 1.00
 * Author      : Matanmi Falana
 * Copyright (c) 2016
 */

public class PatientDetailsDataAdapter extends RecyclerView.Adapter<PatientDetailsDataAdapter.ViewHolder> {
    private ArrayList<String> countries;

    public PatientDetailsDataAdapter(ArrayList<String> countries) {
        this.countries = countries;
    }

    @Override
    public PatientDetailsDataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.patient_details_card, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PatientDetailsDataAdapter.ViewHolder viewHolder, int i) {

        viewHolder.txt_patient_country.setText(countries.get(i));
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txt_patient_country;
        public ViewHolder(View view) {
            super(view);
            txt_patient_country = (TextView)view.findViewById(R.id.txt_patient_country);
        }
    }
}