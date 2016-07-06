package com.matanmi.project.database.table;

/*
 * Table       : Role.java
 * Date        : 2016
 * Version     : 1.00
 * Author      : Matanmi Falana
 * Copyright (c) 2016
 */

public class Role {

    public static final String NAME = "role";

    public static final String ROLE_ID = "id";
    public static final String ROLE_NAME = "name";
    public static final String ROLE_DESC = "desc";

    public static final int ID_ROLE_ID = 0;   
    public static final int ID_ROLE_NAME = 1;
    public static final int ID_ROLE_DESC = 2;

    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + NAME + "("
            + ROLE_ID + " TEXT PRIMARY KEY, " + ROLE_NAME + " TEXT, " + ROLE_DESC +" TEXT)";

    public static final String INSERT_ROLE = "INSERT INTO " + NAME + "(" + ROLE_ID + ", "
            + ROLE_NAME + ", " + ROLE_DESC + ") VALUES (?,?,?)";

    public static final String UPDATE_ROLE = "UPDATE " + NAME + " SET "
            + ROLE_NAME + "=?, " + ROLE_DESC + "=? " + "WHERE "+ ROLE_ID + "=?";

    public static final String DELETE_ROLE = "DELETE FROM " + NAME + " WHERE " + ROLE_ID + "=?";

    public static final String INSERT_PATIENT_ROLE = "INSERT INTO role (id, name, desc) VALUES ('P', 'Patient', 'Patient')";

    public static final String INSERT_DOCTOR_ROLE = "INSERT INTO role (id, name, desc) VALUES ('D', 'Doctor', 'Doctor')";

    public static final String INSERT_ADMIN_ROLE = "INSERT INTO role (id, name, desc) VALUES ('A', 'Administrator', 'Administrator')";
}
