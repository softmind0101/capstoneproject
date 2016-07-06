package com.matanmi.project.database.table;

/*
 * Table       : History.java
 * Date        : 2016
 * Version     : 1.00
 * Author      : Matanmi Falana
 * Copyright (c) 2016
 */

public class History {

    public static final String NAME = "history";

    public static final String HISTORY_ID = "id";
    public static final String HISTORY_PATIENT = "patient";
    public static final String HISTORY_DISEASE = "disease";
    public static final String HISTORY_SYMPTOM = "symptom";
    public static final String HISTORY_DOCTOR = "doctor";
    public static final String HISTORY_MODIFIED = "modified";

    public static final int ID_HISTORY_ID = 0;
    public static final int ID_HISTORY_PATIENT = 1;
    public static final int ID_HISTORY_DISEASE = 2;
    public static final int ID_HISTORY_SYMPTOM = 3;
    public static final int ID_HISTORY_DOCTOR = 4;
    public static final int ID_HISTORY_MODIFIED = 5;

    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + NAME + "("
            + HISTORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
            + HISTORY_PATIENT + " TEXT, " + HISTORY_DISEASE + " TEXT, "
            + HISTORY_SYMPTOM + " TEXT, " + HISTORY_DOCTOR + " TEXT, "
            + HISTORY_MODIFIED + " TEXT)";

    public static final String INSERT_HISTORY = "INSERT INTO " + NAME + "(" + HISTORY_PATIENT + ", "
            + HISTORY_DISEASE + ", " + HISTORY_SYMPTOM + ", " + HISTORY_DOCTOR + ", "
            + HISTORY_MODIFIED + ") VALUES (?,?,?,?,?)";

    public static final String UPDATE_HISTORY = "UPDATE " + NAME + " SET "
            + HISTORY_PATIENT + "=?, " + HISTORY_DISEASE + "=?, " + HISTORY_SYMPTOM + "=?, "
            + HISTORY_DOCTOR + "=?, " + HISTORY_MODIFIED + "=? WHERE "+ HISTORY_ID + "=?";

    public static final String DELETE_HISTORY = "DELETE FROM " + NAME + " WHERE "
            + HISTORY_ID + "=?";

}