package com.matanmi.project;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.matanmi.project.ui.TabAdminAdapter;
import com.matanmi.project.ui.TabDoctorAdapter;
import com.matanmi.project.ui.TabPasswordAdapter;
import com.matanmi.project.ui.TabPatientAdapter;
import com.matanmi.project.ui.TabProfileAdapter;
import com.matanmi.project.util.AbstractActivity;
import com.matanmi.project.util.SessionManager;

import java.util.HashMap;

/*
 * Base        : BaseActivity.java
 * Date        : 2016
 * Version     : 1.00
 * Author      : Matanmi Falana
 * Copyright (c) 2016
 */

public class BaseActivity extends AbstractActivity implements NavigationView.OnNavigationItemSelectedListener {
    SessionManager session;
    HashMap<String, String> user;
    String profile;
    ImageButton logout;
    TextView headerCaption;
    MenuItem navPatient, navDoctor, navAdministrator, navChangeProfile, navChangePassword;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private TabLayout patientTabLayout, doctorTabLayout, adminTabLayout, profileTabLayout, passwordTabLayout;
    private ViewPager patientViewPager, doctorViewPager, adminViewPager, profileViewPager, passwordViewPager;
    private PagerAdapter patientAdapter, doctorAdapter, adminAdapter, profileAdapter, passwordAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        headerCaption = (TextView) findViewById(R.id.txt_header_caption);
        setSupportActionBar(toolbar);

        // Session class instance
        session = new SessionManager(getApplicationContext());

        // get user data from session
        user = session.getUserDetails();

        // user profile
        profile = user.get(SessionManager.KEY_USER);

        logout = (ImageButton) findViewById(R.id.btn_bar_logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.logoutUser();
            }
        });

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setCheckedItem(0);
        navigationView.setNavigationItemSelectedListener(this);

        navPatient = navigationView.getMenu().findItem(R.id.nav_patient);
        navDoctor = navigationView.getMenu().findItem(R.id.nav_doctor);
        navAdministrator = navigationView.getMenu().findItem(R.id.nav_administrator);
        navChangeProfile = navigationView.getMenu().findItem(R.id.nav_view_change_profile);
        navChangePassword = navigationView.getMenu().findItem(R.id.nav_change_password);

        patientTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        doctorTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        adminTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        profileTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        passwordTabLayout = (TabLayout) findViewById(R.id.tab_layout);

        patientViewPager =  (ViewPager) findViewById(R.id.patientPager);
        doctorViewPager =  (ViewPager) findViewById(R.id.doctorPager);
        adminViewPager =  (ViewPager) findViewById(R.id.adminPager);
        profileViewPager =  (ViewPager) findViewById(R.id.profilePager);
        passwordViewPager =  (ViewPager) findViewById(R.id.passwordPager);

        switch (profile) {
            case "P":
                navPatient.setChecked(true);
                navPatient.setEnabled(true);
                navDoctor.setEnabled(false);
                navAdministrator.setEnabled(false);
                headerCaption.setText(navPatient.getTitle());
                getPatientPagerLayout(patientTabLayout, patientViewPager);
                break;
            case "D":
                navPatient.setEnabled(false);
                navDoctor.setChecked(true);
                navDoctor.setEnabled(true);
                navAdministrator.setEnabled(false);
                headerCaption.setText(navDoctor.getTitle());
                getDoctorPagerLayout(doctorTabLayout, doctorViewPager);
                break;
            case "A":
                navPatient.setEnabled(false);
                navDoctor.setEnabled(false);
                navAdministrator.setChecked(true);
                navAdministrator.setEnabled(true);
                headerCaption.setText(navAdministrator.getTitle());
                getAdminPagerLayout(adminTabLayout, adminViewPager);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        //int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //if (id == R.id.action_settings) {
            //return true;
        //}
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_patient) {
            navPatient.setChecked(true);
            navPatient.setEnabled(true);
            navDoctor.setEnabled(false);
            navAdministrator.setEnabled(false);
            headerCaption.setText(navPatient.getTitle());
            getPatientPagerLayout(patientTabLayout, patientViewPager);
        } else if (id == R.id.nav_doctor) {
            navPatient.setEnabled(false);
            navDoctor.setChecked(true);
            navDoctor.setEnabled(true);
            navAdministrator.setEnabled(false);
            headerCaption.setText(navDoctor.getTitle());
            getDoctorPagerLayout(doctorTabLayout, doctorViewPager);
        } else if (id == R.id.nav_administrator) {
            navPatient.setEnabled(false);
            navDoctor.setEnabled(false);
            navAdministrator.setChecked(true);
            navAdministrator.setEnabled(true);
            headerCaption.setText(navAdministrator.getTitle());
            getAdminPagerLayout(adminTabLayout, adminViewPager);
        } else if (id == R.id.nav_change_password) {
            headerCaption.setText(navChangePassword.getTitle());
            getPasswordPagerLayout(passwordTabLayout, passwordViewPager);
        } else if (id == R.id.nav_view_change_profile) {
            headerCaption.setText(navChangeProfile.getTitle());
            getProfilePagerLayout(profileTabLayout, profileViewPager);
        } else if (id == R.id.nav_logout) {
            getLogoutPagerLayout();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void getPatientPagerLayout(TabLayout tabLayout, ViewPager viewPager){
        tabLayout.removeAllTabs();
        tabLayout.addTab(tabLayout.newTab().setText("Details"));
        tabLayout.addTab(tabLayout.newTab().setText("Predict"));
        tabLayout.addTab(tabLayout.newTab().setText("Doctor"));
        tabLayout.addTab(tabLayout.newTab().setText("Feedback"));

        patientAdapter = new TabPatientAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        patientAdapter.notifyDataSetChanged();
        viewPager.setAdapter(patientAdapter);
        patientViewPager.setVisibility(View.VISIBLE);
        doctorViewPager.setVisibility(View.INVISIBLE);
        adminViewPager.setVisibility(View.INVISIBLE);
        profileViewPager.setVisibility(View.INVISIBLE);
        passwordViewPager.setVisibility(View.INVISIBLE);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                patientViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public void getDoctorPagerLayout(TabLayout tabLayout, ViewPager viewPager){
        tabLayout.removeAllTabs();
        tabLayout.addTab(tabLayout.newTab().setText("Details"));
        tabLayout.addTab(tabLayout.newTab().setText("Previous"));

        doctorAdapter = new TabDoctorAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        doctorAdapter.notifyDataSetChanged();
        viewPager.setAdapter(doctorAdapter);
        patientViewPager.setVisibility(View.INVISIBLE);
        doctorViewPager.setVisibility(View.VISIBLE);
        adminViewPager.setVisibility(View.INVISIBLE);
        profileViewPager.setVisibility(View.INVISIBLE);
        passwordViewPager.setVisibility(View.INVISIBLE);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                doctorViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public void getAdminPagerLayout(TabLayout tabLayout, ViewPager viewPager){
        tabLayout.removeAllTabs();
        tabLayout.addTab(tabLayout.newTab().setText("Patient"));
        tabLayout.addTab(tabLayout.newTab().setText("Doctor"));
        tabLayout.addTab(tabLayout.newTab().setText("Disease"));
        tabLayout.addTab(tabLayout.newTab().setText("Feedback"));

        adminAdapter = new TabAdminAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        adminAdapter.notifyDataSetChanged();
        viewPager.setAdapter(adminAdapter);
        viewPager.setOffscreenPageLimit(3);
        patientViewPager.setVisibility(View.INVISIBLE);
        doctorViewPager.setVisibility(View.INVISIBLE);
        adminViewPager.setVisibility(View.VISIBLE);
        profileViewPager.setVisibility(View.INVISIBLE);
        passwordViewPager.setVisibility(View.INVISIBLE);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                adminViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public void getProfilePagerLayout(TabLayout tabLayout, ViewPager viewPager){
        tabLayout.removeAllTabs();
        tabLayout.addTab(tabLayout.newTab().setText("View"));
        tabLayout.addTab(tabLayout.newTab().setText("Edit"));
        tabLayout.addTab(tabLayout.newTab().setText("Change Picture"));

        profileAdapter = new TabProfileAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        profileAdapter.notifyDataSetChanged();
        viewPager.setAdapter(profileAdapter);
        patientViewPager.setVisibility(View.INVISIBLE);
        doctorViewPager.setVisibility(View.INVISIBLE);
        adminViewPager.setVisibility(View.INVISIBLE);
        profileViewPager.setVisibility(View.VISIBLE);
        passwordViewPager.setVisibility(View.INVISIBLE);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                profileViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public void getPasswordPagerLayout(TabLayout tabLayout, ViewPager viewPager){
        tabLayout.removeAllTabs();
        tabLayout.addTab(tabLayout.newTab().setText("Change Password"));

        passwordAdapter = new TabPasswordAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(passwordAdapter);
        patientViewPager.setVisibility(View.INVISIBLE);
        doctorViewPager.setVisibility(View.INVISIBLE);
        adminViewPager.setVisibility(View.INVISIBLE);
        profileViewPager.setVisibility(View.INVISIBLE);
        passwordViewPager.setVisibility(View.VISIBLE);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                passwordViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public void getLogoutPagerLayout() {
        session.logoutUser();
    }
}
