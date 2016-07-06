package com.matanmi.project.model;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.matanmi.project.database.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

/*
 * Model       : Category.java
 * Date        : 2016
 * Version     : 1.00
 * Author      : Matanmi Falana
 * Copyright (c) 2016
 */

public class Category extends com.matanmi.project.database.table.Category {

    public int id;
    public String doctor;
    public String major;
    private SQLiteStatement stmt = null;
    private SQLiteDatabase db = null;
    private Category categoryRecord;
    private Cursor cursor;
    private List<Category> items = null;
    private long numberOfRowAffected = 0;

    public Category() {
        doctor = major = "";
        id = 0;
        db = DatabaseHelper.getDatabase();
    }

    public Category(Cursor rs) throws SQLException {
        id = rs.getInt(ID_CATEGORY_ID);
        doctor = rs.getString(ID_CATEGORY_DOCTOR);
        major = rs.getString(ID_CATEGORY_MAJOR);
    }

    public boolean insert() {
        try {
            stmt = db.compileStatement(INSERT_CATEGORY);
            stmt.bindString(1, doctor);
            stmt.bindString(2, major);
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
            stmt = db.compileStatement(UPDATE_CATEGORY);
            stmt.bindString(1, doctor);
            stmt.bindString(2, major);
            stmt.bindLong(3, id);
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
            stmt = db.compileStatement(DELETE_CATEGORY);
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
        categoryRecord = new Category();
        categoryRecord.doctor = this.doctor;
        categoryRecord.major = this.major;
        return insertCategory(categoryRecord);
    }

    public boolean editStore() {
        categoryRecord = new Category();
        categoryRecord.id = this.id;
        categoryRecord.doctor = this.doctor;
        categoryRecord.major = this.major;
        return editCategory(categoryRecord);
    }

    public boolean deleteStore() {
        categoryRecord = new Category();
        categoryRecord.id = this.id;
        return deleteCategory(categoryRecord);
    }

    public boolean insertCategory(Category obj) {
        return obj.insert();
    }

    public boolean editCategory(Category obj) {
        return obj.edit();
    }

    public boolean deleteCategory(Category obj) {
        return obj.delete(obj.id);
    }

    public boolean isCategoryExist(String param1, String param2) {
        boolean categoryExist = false;
        try {
            cursor = db.rawQuery("SELECT * FROM CATEGORY WHERE doctor=? AND major=?", new String[] {param1, param2});
            if (cursor.moveToNext()) {
                categoryExist = true;
            }
            cursor.close();
        } catch (Exception e){
            categoryExist = false;
        } finally {
            db.close();
        }
        return categoryExist;
    }

    public Category getCategoryRecord(int id) {
        try {
            cursor = db.rawQuery("SELECT * FROM CATEGORY WHERE id=?", new String[] {String.valueOf(id)});
            if (cursor.moveToNext()) {
                categoryRecord = new Category(cursor);
            }
            cursor.close();
        } catch (Exception e){
            categoryRecord = null;
        } finally {
            db.close();
        }
        return categoryRecord;
    }

    public List<Category> getCategoryRecords() {
        items = new ArrayList<>();
        try {
            cursor = db.rawQuery("SELECT * FROM CATEGORY ORDER BY id", null);
            while (cursor.moveToNext()) {
                items.add(new Category(cursor));
            }
            cursor.close();
        } catch (Exception e){
            categoryRecord = null;
        } finally {
            db.close();
        }
        return items;
    }

    public List<Category> getCategoryRecords(int nStart, int nSize) {
        items = new ArrayList<>();
        try {
            cursor = db.rawQuery("SELECT * FROM CATEGORY ORDER BY id LIMIT ?,?", new String[] {String.valueOf(nStart), String.valueOf(nSize)});
            while (cursor.moveToNext()) {
                items.add(new Category(cursor));
            }
            cursor.close();
        } catch (Exception e){
            categoryRecord = null;
        } finally {
            db.close();
        }
        return items;
    }

    public List<Category> getCategoryRecords(String column, String value, int nStart, int nSize) {
        String sql = "";
        String searchingValue = "%" + value + "%";
        items = new ArrayList<>();
        try {
            if (column.equals("")) {
                sql = "SELECT * FROM CATEGORY WHERE doctor LIKE '"
                        + searchingValue + "' OR major LIKE '"
                        + searchingValue + "') LIMIT ?,?";
            } else {
                sql = "SELECT * FROM CATEGORY WHERE "
                        + column + " LIKE '" + searchingValue
                        + "' LIMIT ?,?";
            }
            cursor = db.rawQuery(sql, new String[] {String.valueOf(nStart), String.valueOf(nSize)});
            while (cursor.moveToNext()) {
                items.add(new Category(cursor));
            }
            cursor.close();
        } catch (Exception e){
            categoryRecord = null;
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

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

}