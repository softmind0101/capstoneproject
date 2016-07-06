package com.matanmi.project.database.table;

/*
 * Table       : Disease.java
 * Date        : 2016
 * Version     : 1.00
 * Author      : Matanmi Falana
 * Copyright (c) 2016
 */

public class Disease {

    public static final String NAME = "disease";

    public static final String DISEASE_ID = "id";
    public static final String DISEASE_NAME = "name";
    public static final String DISEASE_TYPE = "type";
    public static final String DISEASE_DESC = "desc";

    public static final int ID_DISEASE_ID = 0;
    public static final int ID_DISEASE_NAME = 1;
    public static final int ID_DISEASE_TYPE = 2;
    public static final int ID_DISEASE_DESC = 3;

    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + NAME + "("
            + DISEASE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
            + DISEASE_NAME + " TEXT, " + DISEASE_TYPE + " TEXT, " + DISEASE_DESC + " TEXT)";

    public static final String INSERT_DISEASE = "INSERT INTO " + NAME + "(" + DISEASE_NAME + ", "
            + DISEASE_TYPE + ", " + DISEASE_DESC + ") VALUES (?,?,?)";

    public static final String UPDATE_DISEASE = "UPDATE " + NAME + " SET "
            + DISEASE_NAME + "=?, " + DISEASE_TYPE + "=?, " + DISEASE_DESC + "=? "
            + "WHERE "+ DISEASE_ID + "=?";

    public static final String DELETE_DISEASE = "DELETE FROM " + NAME + " WHERE "
            + DISEASE_ID + "=?";

}