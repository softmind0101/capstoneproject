package com.matanmi.project.database.table;

public class Category {

    public static final String NAME = "category";

    public static final String CATEGORY_ID = "id";
    public static final String CATEGORY_DOCTOR = "name";
    public static final String CATEGORY_MAJOR = "major";

    public static final int ID_CATEGORY_ID = 0;
    public static final int ID_CATEGORY_DOCTOR = 1;
    public static final int ID_CATEGORY_MAJOR = 2;

    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + NAME + "("
            + CATEGORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
            + CATEGORY_DOCTOR + " TEXT, " + CATEGORY_MAJOR + " TEXT)";

    public static final String INSERT_CATEGORY = "INSERT INTO " + NAME + "(" + CATEGORY_DOCTOR + ", "
            + CATEGORY_MAJOR + ") VALUES (?,?)";

    public static final String UPDATE_CATEGORY = "UPDATE " + NAME + " SET "
            + CATEGORY_DOCTOR + "=?, " + CATEGORY_MAJOR + "=? WHERE " + CATEGORY_ID + "=?";

    public static final String DELETE_CATEGORY = "DELETE FROM " + NAME + " WHERE "
            + CATEGORY_ID + "=?";

}