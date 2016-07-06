package com.matanmi.project.model;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.matanmi.project.database.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class Feedback extends com.matanmi.project.database.table.Feedback {

    public int id;
    public String sender;
    public String desc;
    public String receiver;
    public String modified;
    private SQLiteStatement stmt = null;
    private SQLiteDatabase db = null;
    private Feedback feedbackRecord;
    private Cursor cursor;
    private List<Feedback> items = null;
    private long numberOfRowAffected = 0;

    public Feedback() {
        sender = desc = receiver = modified = "";
        id = 0;
        db = DatabaseHelper.getDatabase();
    }

    public Feedback(Cursor rs) throws SQLException {
        id = rs.getInt(ID_FEEDBACK_ID);
        sender = rs.getString(ID_FEEDBACK_SENDER);
        desc = rs.getString(ID_FEEDBACK_DESC);
        receiver = rs.getString(ID_FEEDBACK_RECEIVER);
        modified = rs.getString(ID_FEEDBACK_MODIFIED);
    }

    public boolean insert() {
        try {
            stmt = db.compileStatement(INSERT_FEEDBACK);
            stmt.bindString(1, sender);
            stmt.bindString(2, desc);
            stmt.bindString(3, receiver);
            stmt.bindString(4, modified);
            numberOfRowAffected = stmt.executeInsert();
            stmt.close();
        } catch (Exception e) {
            return false;
        }

        if (numberOfRowAffected > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean edit() {
        try {
            stmt = db.compileStatement(UPDATE_FEEDBACK);
            stmt.bindString(1, sender);
            stmt.bindString(2, desc);
            stmt.bindString(3, receiver);
            stmt.bindString(4, modified);
            stmt.bindLong(5, id);
            numberOfRowAffected = stmt.executeUpdateDelete();
            stmt.close();
        } catch (Exception e) {
            return false;
        }

        if (numberOfRowAffected > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean delete(int id) {
        try {
            stmt = db.compileStatement(DELETE_FEEDBACK);
            stmt.bindLong(1, id);
            numberOfRowAffected = stmt.executeUpdateDelete();
            stmt.close();
        } catch (Exception e) {
            return false;
        }

        if (numberOfRowAffected > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean addStore() {
        feedbackRecord = new Feedback();
        feedbackRecord.sender = this.sender;
        feedbackRecord.desc = this.desc;
        feedbackRecord.receiver = this.receiver;
        feedbackRecord.modified = this.modified;
        return insertFeedback(feedbackRecord);
    }

    public boolean editStore() {
        feedbackRecord = new Feedback();
        feedbackRecord.id = this.id;
        feedbackRecord.sender = this.sender;
        feedbackRecord.desc = this.desc;
        feedbackRecord.receiver = this.receiver;
        feedbackRecord.modified = this.modified;
        return editFeedback(feedbackRecord);
    }

    public boolean deleteStore() {
        feedbackRecord = new Feedback();
        feedbackRecord.id = this.id;
        return deleteFeedback(feedbackRecord);
    }

    public boolean insertFeedback(Feedback obj) {
        return obj.insert();
    }

    public boolean editFeedback(Feedback obj) {
        return obj.edit();
    }

    public boolean deleteFeedback(Feedback obj) {
        return obj.delete(obj.id);
    }

    public Feedback getFeedbackRecord(int id) {
        try {
            cursor = db.rawQuery("SELECT * FROM Feedback WHERE id=?", new String[] {String.valueOf(id)});
            if (cursor.moveToNext()) {
                feedbackRecord = new Feedback(cursor);
            }
            cursor.close();
        } catch (Exception e){
            feedbackRecord = null;
        } finally {
            db.close();
        }
        return feedbackRecord;
    }

    public List<Feedback> getFeedbackRecords() {
        items = new ArrayList<>();
        try {
            cursor = db.rawQuery("SELECT * FROM Feedback ORDER BY id", null);
            while (cursor.moveToNext()) {
                items.add(new Feedback(cursor));
            }
            cursor.close();
        } catch (Exception e){
            feedbackRecord = null;
        } finally {
            db.close();
        }
        return items;
    }

    public List<Feedback> getFeedbackRecords(int nStart, int nSize) {
        items = new ArrayList<>();
        try {
            cursor = db.rawQuery("SELECT * FROM Feedback ORDER BY id LIMIT ?,?", new String[] {String.valueOf(nStart), String.valueOf(nSize)});
            while (cursor.moveToNext()) {
                items.add(new Feedback(cursor));
            }
            cursor.close();
        } catch (Exception e){
            feedbackRecord = null;
        } finally {
            db.close();
        }
        return items;
    }

    public List<Feedback> getFeedbackRecords(String column, String value, int nStart, int nSize) {
        String sql = "";
        String searchingValue = "%" + value + "%";
        items = new ArrayList<>();
        try {
            if (column.equals("")) {
                sql = "SELECT * FROM Feedback WHERE sender LIKE '"
                        + searchingValue + "' OR desc LIKE '"
                        + searchingValue + "' OR receiver LIKE '"
                        + searchingValue + "' OR modified LIKE '"
                        + searchingValue + "') LIMIT ?,?";
            } else {
                sql = "SELECT * FROM Feedback WHERE "
                        + column + " LIKE '" + searchingValue
                        + "' LIMIT ?,?";
            }
            cursor = db.rawQuery(sql, new String[] {String.valueOf(nStart), String.valueOf(nSize)});
            while (cursor.moveToNext()) {
                items.add(new Feedback(cursor));
            }
            cursor.close();
        } catch (Exception e){
            feedbackRecord = null;
        } finally {
            db.close();
        }
        return items;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }
}