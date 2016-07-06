package com.matanmi.project.database.table;

public class Feedback {

    public static final String NAME = "feedback";

    public static final String FEEDBACK_ID = "id";
    public static final String FEEDBACK_SENDER = "sender";
    public static final String FEEDBACK_DESC = "desc";
    public static final String FEEDBACK_RECEIVER = "receiver";
    public static final String FEEDBACK_MODIFIED = "modified";

    public static final int ID_FEEDBACK_ID = 0;
    public static final int ID_FEEDBACK_SENDER = 1;
    public static final int ID_FEEDBACK_DESC = 2;
    public static final int ID_FEEDBACK_RECEIVER = 3;
    public static final int ID_FEEDBACK_MODIFIED = 4;

    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + NAME + "("
            + FEEDBACK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
            + FEEDBACK_SENDER + " TEXT, " + FEEDBACK_DESC + " TEXT, " + FEEDBACK_RECEIVER + " TEXT, "
            + FEEDBACK_MODIFIED + " TEXT)";

    public static final String INSERT_FEEDBACK = "INSERT INTO " + NAME + "(" + FEEDBACK_SENDER + ", "
            + FEEDBACK_DESC + ", " + FEEDBACK_RECEIVER + ", " + FEEDBACK_MODIFIED
            + ") VALUES (?,?,?,?)";

    public static final String UPDATE_FEEDBACK = "UPDATE " + NAME + " SET "
            + FEEDBACK_SENDER + "=?, " + FEEDBACK_DESC + "=?, " + FEEDBACK_RECEIVER + "=?, "
            + FEEDBACK_MODIFIED + "=? WHERE "+ FEEDBACK_ID + "=?";

    public static final String DELETE_FEEDBACK = "DELETE FROM " + NAME + " WHERE "
            + FEEDBACK_ID + "=?";

}