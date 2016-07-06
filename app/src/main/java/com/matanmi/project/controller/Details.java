package com.matanmi.project.controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.matanmi.project.R;

import java.util.ArrayList;

public class Details extends AppCompatActivity {
    private ArrayList<String> countries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_details);
        initViews();
    }

    private void initViews(){
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.patient_card_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        countries = new ArrayList<>();
        countries.add("Tesing 1");
        countries.add("Tesing 2");
        countries.add("Tesing 3");
        countries.add("Tesing 4");
        countries.add("Tesing 5");
        System.out.println("List of Countries Created");
        RecyclerView.Adapter adapter = new PatientDataAdapter(countries);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            GestureDetector gestureDetector = new GestureDetector(getApplicationContext(), new GestureDetector.SimpleOnGestureListener() {

                @Override public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

            });
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

                View child = rv.findChildViewUnder(e.getX(), e.getY());
                if(child != null && gestureDetector.onTouchEvent(e)) {
                    int position = rv.getChildAdapterPosition(child);
                    Toast.makeText(getApplicationContext(), countries.get(position), Toast.LENGTH_SHORT).show();
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
    }
}