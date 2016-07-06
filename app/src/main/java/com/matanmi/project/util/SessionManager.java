package com.matanmi.project.util;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.matanmi.project.model.Profile;
import com.matanmi.project.view.login.LoginActivity;

import java.util.HashMap;

public class SessionManager {
    // Shared Preferences
    SharedPreferences pref;
    // Editor for Shared preferences
    Editor editor;
    // Context
    Context _context;
    // Shared pref mode
    int PRIVATE_MODE = 0;
    // Sharedpref file name
    private static final String PREF_NAME = "Capstone";
    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";
    // Make variable public to access from outside
    public static final String KEY_USER = "user";
    public static final String KEY_NAME = "username";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_AGE = "age";
    public static final String KEY_GENDER = "gender";

    // Constructor
    public SessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    /**
     * Create login session
     * */
    public void createLoginSession(Profile profile){
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);
        // Identify user in pref
        editor.putString(KEY_USER, profile.role);
        // Storing name in pref
        editor.putString(KEY_NAME, profile.name);
        // Storing email in pref
        editor.putString(KEY_EMAIL, profile.email);
        // Storing age in pref
        editor.putInt(KEY_AGE, profile.age);
        // Storing gender in pref
        editor.putString(KEY_GENDER, profile.gender);

        // commit changes
        editor.commit();
    }

    /**
     * Create login session
     * */
    public void createLoginSession(String user, String username, String email, int age, String gender){
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);
        // Identify user in pref
        editor.putString(KEY_USER, user);
        // Storing name in pref
        editor.putString(KEY_NAME, username);
        // Storing email in pref
        editor.putString(KEY_EMAIL, email);
        // Storing age in pref
        editor.putInt(KEY_AGE, age);
        // Storing gender in pref
        editor.putString(KEY_GENDER, gender);

        // commit changes
        editor.commit();
    }

    /**
     * Check login method wil check user login status
     * If false it will redirect user to login page
     * Else won't do anything
     * */
    public void checkLogin(){
        // Check login status
        if (!this.isLoggedIn()) {
            // user is not logged in redirect him to Login Activity
            Intent intentInstance = new Intent(_context, LoginActivity.class);
            // Closing all the Activities
            intentInstance.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            // Add new Flag to start new Activity
            intentInstance.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            // Staring Login Activity
            _context.startActivity(intentInstance);
        }
    }

    /**
     * Get stored session data
     * */
    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        // user profile
        user.put(KEY_USER, pref.getString(KEY_USER, null));
        // user name
        user.put(KEY_NAME, pref.getString(KEY_NAME, null));
        // user email
        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));
        // user age
        user.put(KEY_AGE, String.valueOf(pref.getInt(KEY_AGE, 0)));
        // user gender
        user.put(KEY_GENDER, pref.getString(KEY_GENDER, null));

        // return user
        return user;
    }

    /**
     * Clear session details
     * */
    public void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Loing Activity
        Intent intentInstance = new Intent(_context, LoginActivity.class);
        // Closing all the Activities
        intentInstance.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        // Add new Flag to start new Activity
        intentInstance.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        // Staring Login Activity
        _context.startActivity(intentInstance);
    }

    /**
     * Quick check for login
     * **/
    // Get Login State
    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }
}