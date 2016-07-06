package com.matanmi.project.database.table;

/*
 * Table       : Profile.java
 * Date        : 2016
 * Version     : 1.00
 * Author      : Matanmi Falana
 * Copyright (c) 2016
 */

public class Profile {

    public static final String NAME = "profile";

    public static final String PROFILE_ID = "id";
    public static final String PROFILE_NAME = "name";
    public static final String PROFILE_EMAIL = "email";
    public static final String PROFILE_MOBILE = "mobile";
    public static final String PROFILE_AGE = "age";
    public static final String PROFILE_GENDER = "gender";
    public static final String PROFILE_ROLE = "role";
    public static final String PROFILE_PASSWORD = "password";

    public static final int ID_PROFILE_ID = 0;
    public static final int ID_PROFILE_NAME = 1;
    public static final int ID_PROFILE_EMAIL = 2;
    public static final int ID_PROFILE_MOBILE = 3;
    public static final int ID_PROFILE_AGE = 4;
    public static final int ID_PROFILE_GENDER = 5;
    public static final int ID_PROFILE_ROLE = 6;
    public static final int ID_PROFILE_PASSWORD = 7;

    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + NAME + "("
            + PROFILE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
            + PROFILE_NAME + " TEXT, " + PROFILE_EMAIL + " TEXT, " + PROFILE_MOBILE + " TEXT, "
            + PROFILE_AGE + " INTEGER, " + PROFILE_GENDER + " TEXT, " + PROFILE_ROLE + " TEXT, "
            + PROFILE_PASSWORD + " TEXT)";

    public static final String INSERT_PROFILE = "INSERT INTO " + NAME + "(" + PROFILE_NAME + ", "
            + PROFILE_EMAIL + ", " + PROFILE_MOBILE + ", " + PROFILE_AGE + ", "
            + PROFILE_GENDER + ", " + PROFILE_ROLE + ", " + PROFILE_PASSWORD
            + ") VALUES (?,?,?,?,?,?,?)";

    public static final String UPDATE_PROFILE = "UPDATE " + NAME + " SET "
            + PROFILE_NAME + "=?, " + PROFILE_EMAIL + "=?, " + PROFILE_MOBILE + "=?, "
            + PROFILE_AGE + "=?, " + PROFILE_GENDER + "=?, " + PROFILE_ROLE + "=?, "
            + PROFILE_PASSWORD + "=? WHERE "+ PROFILE_ID + "=?";

    public static final String DELETE_PROFILE = "DELETE FROM " + NAME + " WHERE "
            + PROFILE_ID + "=?";

    public static final String INSERT_ADMIN_PROFILE = "INSERT INTO " + NAME
            + " (name, email, mobile, age, gender, role, password)"
            + " VALUES ('Matanmi', 'softmind0101@yahoo.com', '08124748566', '22', 'M', 'A', 'admin')";

    public static final String LOGIN_CHECK = "SELECT * FROM " + NAME + " WHERE (" + PROFILE_EMAIL
            + "=? OR " + PROFILE_MOBILE + "=?) AND " + PROFILE_PASSWORD + "=?";
}