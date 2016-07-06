package com.matanmi.project.database.table;

/*
 * Table       : Photograph.java
 * Date        : 2016
 * Version     : 1.00
 * Author      : Matanmi Falana
 * Copyright (c) 2016
 */

public class Photograph {

    public static final String NAME = "photograph";

    public static final String PHOTOGRAPH_ID = "id";
    public static final String PHOTOGRAPH_TYPE = "type";
    public static final String PHOTOGRAPH_PHOTO = "photo";

    public static final int ID_PHOTOGRAPH_ID = 0;
    public static final int ID_PHOTOGRAPH_TYPE = 1;
    public static final int ID_PHOTOGRAPH_PHOTO = 2;

    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + NAME + "("
            + PHOTOGRAPH_ID + " TEXT PRIMARY KEY NOT NULL, "
            + PHOTOGRAPH_TYPE + " TEXT, " + PHOTOGRAPH_PHOTO + " BLOB)";

    public static final String INSERT_PHOTOGRAPH = "INSERT INTO " + NAME + "("
            + PHOTOGRAPH_ID + ", " + PHOTOGRAPH_TYPE + ", " + PHOTOGRAPH_PHOTO
            + ") VALUES (?,?,?)";

    public static final String UPDATE_PHOTOGRAPH = "UPDATE " + NAME + " SET "
            + PHOTOGRAPH_TYPE + "=?, " + PHOTOGRAPH_PHOTO + "=? "
            + "WHERE "+ PHOTOGRAPH_ID + "=?";

    public static final String DELETE_PHOTOGRAPH = "DELETE FROM " + NAME + " WHERE "
            + PHOTOGRAPH_ID + "=?";
}