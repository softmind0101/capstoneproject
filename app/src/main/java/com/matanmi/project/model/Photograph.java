package com.matanmi.project.model;

import android.app.Activity;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.matanmi.project.database.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

/*
 * Model       : Photograph.java
 * Date        : 2016
 * Version     : 1.00
 * Author      : Matanmi Falana
 * Copyright (c) 2016
 */

public class Photograph extends Activity {

    public String id;
    public String type;
    public byte[] photo;
    private SQLiteStatement stmt = null;
    private SQLiteDatabase db = null;
    private Photograph photographRecord;
    private Cursor cursor;
    private List<Photograph> items = null;
    private long numberOfRowAffected = 0;
    private static com.matanmi.project.database.table.Photograph photographTable;

    public Photograph() {
        id = type = "";
        photo = null;
        db = DatabaseHelper.getDatabase();
    }

    public Photograph(Cursor rs) throws SQLException {
        id = rs.getString(photographTable.ID_PHOTOGRAPH_ID);
        type = rs.getString(photographTable.ID_PHOTOGRAPH_TYPE);
        photo = rs.getBlob(photographTable.ID_PHOTOGRAPH_PHOTO);
    }

    public boolean insert() {
        try {
            stmt = db.compileStatement(photographTable.INSERT_PHOTOGRAPH);
            stmt.bindString(1, id);
            stmt.bindString(2, type);
            stmt.bindBlob(3, photo);
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
            stmt = db.compileStatement(photographTable.UPDATE_PHOTOGRAPH);
            stmt.bindString(1, type);
            stmt.bindBlob(2, photo);
            stmt.bindString(3, id);
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

    public boolean delete(String id) {
        try {
            stmt = db.compileStatement(photographTable.DELETE_PHOTOGRAPH);
            stmt.bindString(1, id);
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
        photographRecord = new Photograph();
        photographRecord.id = this.id;
        photographRecord.type = this.type;
        photographRecord.photo = this.photo;
        return insertPhotograph(photographRecord);
    }

    public boolean editStore() {
        photographRecord = new Photograph();
        photographRecord.id = this.id;
        photographRecord.type = this.type;
        photographRecord.photo = this.photo;
        return editPhotograph(photographRecord);
    }

    public boolean deleteStore() {
        photographRecord = new Photograph();
        photographRecord.id = this.id;
        return deletePhotograph(photographRecord);
    }

    public boolean insertPhotograph(Photograph obj) {
        return obj.insert();
    }

    public boolean editPhotograph(Photograph obj) {
        return obj.edit();
    }

    public boolean deletePhotograph(Photograph obj) {
        return obj.delete(obj.id);
    }

    public boolean isPhotographExist(String param) {
        boolean photographExist = false;
        try {
            cursor = db.rawQuery("SELECT * FROM PHOTOGRAPH WHERE id=?", new String[] {param});
            if (cursor.moveToNext()) {
                photographExist = true;
            }
            cursor.close();
        } catch (Exception e){
            photographExist = false;
        } finally {
            db.close();
        }
        return photographExist;
    }

    public Photograph getPhotographRecord(String param) {
        try {
            cursor = db.rawQuery("SELECT * FROM PHOTOGRAPH WHERE id=?", new String[] {param});
            if (cursor.moveToNext()) {
                photographRecord = new Photograph(cursor);
            }
            cursor.close();
        } catch (Exception e){
            photographRecord = null;
        } finally {
            db.close();
        }
        return photographRecord;
    }

    public List<Photograph> getPhotographRecords() {
        items = new ArrayList<>();
        try {
            cursor = db.rawQuery("SELECT * FROM PHOTOGRAPH ORDER BY id", null);
            while (cursor.moveToNext()) {
                items.add(new Photograph(cursor));
            }
            cursor.close();
        } catch (Exception e){
            photographRecord = null;
        } finally {
            db.close();
        }
        return items;
    }

    public List<Photograph> getPhotographRecords(int nStart, int nSize) {
        items = new ArrayList<>();
        try {
            cursor = db.rawQuery("SELECT * FROM PHOTOGRAPH ORDER BY id LIMIT ?,?", new String[] {String.valueOf(nStart), String.valueOf(nSize)});
            while (cursor.moveToNext()) {
                items.add(new Photograph(cursor));
            }
            cursor.close();
        } catch (Exception e){
            photographRecord = null;
        } finally {
            db.close();
        }
        return items;
    }

    public List<Photograph> getPhotographRecords(String column, String value, int nStart, int nSize) {
        String sql = "";
        String searchingValue = "%" + value + "%";
        items = new ArrayList<>();
        try {
            if (column.equals("")) {
                sql = "SELECT * FROM PHOTOGRAPH WHERE id LIKE '"
                        + searchingValue + "' OR type LIKE '"
                        + searchingValue + "') LIMIT ?,?";
            } else {
                sql = "SELECT * FROM PHOTOGRAPH WHERE "
                        + column + " LIKE '" + searchingValue
                        + "' LIMIT ?,?";
            }
            cursor = db.rawQuery(sql, new String[] {String.valueOf(nStart), String.valueOf(nSize)});
            while (cursor.moveToNext()) {
                items.add(new Photograph(cursor));
            }
            cursor.close();
        } catch (Exception e){
            photographRecord = null;
        } finally {
            db.close();
        }
        return items;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }
}