package com.matanmi.project.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.matanmi.project.database.table.Category;
import com.matanmi.project.database.table.Disease;
import com.matanmi.project.database.table.Feedback;
import com.matanmi.project.database.table.History;
import com.matanmi.project.database.table.Photograph;
import com.matanmi.project.database.table.Profile;
import com.matanmi.project.database.table.Role;
import com.matanmi.project.util.Init;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "capstone";
    private static DatabaseHelper instance = null;
    private static SQLiteDatabase db = null;
    private static final int DB_VERSION = 2;
    public static final String CLEAR_TABLE_DATA = "DELETE FROM ";
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS ";

    public DatabaseHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion == 1 && newVersion == 2){

        }
    }

    public static synchronized DatabaseHelper getInstance(Context context){
        // Use the application context, which will ensure that you don't accidentally leak an Activity's context.
        if (instance == null) {
            instance = new DatabaseHelper(context.getApplicationContext());
        }
        return instance;
    }

    public static synchronized DatabaseHelper getInstance(){
        if(instance == null){
            instance = new DatabaseHelper(Init.mContext, DB_NAME, null, DB_VERSION);
        }
        return instance;
    }

    public static synchronized SQLiteDatabase getDatabase(){
        if (db == null){
            db = getInstance().getWritableDatabase();
        }
        return db;
    }

    public static synchronized void closeDatabase() {
        // private static AtomicInteger openCounter = new AtomicInteger();
        // if(openCounter.decrementAndGet() == 0) { db.close(); }
        // Alternative uses : db != null
        if(db != null) {
            // Closing database
            db.close();
        }
    }

    public void loadScripts(SQLiteDatabase db){
        // Drop Tables
        db.execSQL(DROP_TABLE + Category.NAME);
        db.execSQL(DROP_TABLE + Disease.NAME);
        db.execSQL(DROP_TABLE + Feedback.NAME);
        db.execSQL(DROP_TABLE + History.NAME);
        db.execSQL(DROP_TABLE + Photograph.NAME);
        db.execSQL(DROP_TABLE + Profile.NAME);
        db.execSQL(DROP_TABLE + Role.NAME);

        // Create Tables
        db.execSQL(Category.CREATE_TABLE);
        db.execSQL(Disease.CREATE_TABLE);
        db.execSQL(Feedback.CREATE_TABLE);
        db.execSQL(History.CREATE_TABLE);
        db.execSQL(Photograph.CREATE_TABLE);
        db.execSQL(Profile.CREATE_TABLE);
        db.execSQL(Role.CREATE_TABLE);

        // Insert Data
        db.execSQL(Profile.INSERT_ADMIN_PROFILE);
        db.execSQL(Role.INSERT_PATIENT_ROLE);
        db.execSQL(Role.INSERT_DOCTOR_ROLE);
        db.execSQL(Role.INSERT_ADMIN_ROLE);
    }

    public static void exeSQL(String sql, String ... params){
        getDatabase().execSQL(sql, params);
    }

    public static void exeSQL(String sql){
        getDatabase().execSQL(sql);
    }

    public static void insert(String table, ContentValues cv){
        getDatabase().insert(table, null, cv);
    }


    public static Cursor selectAll(String table){
        return getDatabase().query(table, null, null, null, null, null, null);
    }

    public static void clearTable(String table){
        exeSQL(CLEAR_TABLE_DATA + table);
    }
}