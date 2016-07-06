package com.matanmi.project.model;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.matanmi.project.database.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

/*
 * Model       : History.java
 * Date        : 2016
 * Version     : 1.00
 * Author      : Matanmi Falana
 * Copyright (c) 2016
 */

public class History extends com.matanmi.project.database.table.History {

    public int id;
    public String patient;
    public String disease;
    public String symptom;
    public String doctor;
    public String modified;
    private SQLiteStatement stmt = null;
    private SQLiteDatabase db = null;
    private History historyRecord;
    private Cursor cursor;
    private List<History> items = null;
    private long numberOfRowAffected = 0;

    public History() {
        patient = disease = symptom = "";
        doctor = modified = "";
        id = 0;
        db = DatabaseHelper.getDatabase();
    }

    public History(Cursor rs) throws SQLException {
        id = rs.getInt(ID_HISTORY_ID);
        patient = rs.getString(ID_HISTORY_PATIENT);
        disease = rs.getString(ID_HISTORY_DISEASE);
        symptom = rs.getString(ID_HISTORY_SYMPTOM);
        doctor = rs.getString(ID_HISTORY_DOCTOR);
        modified = rs.getString(ID_HISTORY_MODIFIED);
    }

    public boolean insert() {
        try {
            stmt = db.compileStatement(INSERT_HISTORY);
            stmt.bindString(1, patient);
            stmt.bindString(2, disease);
            stmt.bindString(3, symptom);
            stmt.bindString(4, doctor);
            stmt.bindString(5, modified);
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
            stmt = db.compileStatement(UPDATE_HISTORY);
            stmt.bindString(1, patient);
            stmt.bindString(2, disease);
            stmt.bindString(3, symptom);
            stmt.bindString(4, doctor);
            stmt.bindString(5, modified);
            stmt.bindLong(6, id);
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
            stmt = db.compileStatement(DELETE_HISTORY);
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
        historyRecord = new History();
        historyRecord.patient = this.patient;
        historyRecord.disease = this.disease;
        historyRecord.symptom = this.symptom;
        historyRecord.doctor = this.doctor;
        historyRecord.modified = this.modified;
        return insertHistory(historyRecord);
    }

    public boolean editStore() {
        historyRecord = new History();
        historyRecord.id = this.id;
        historyRecord.patient = this.patient;
        historyRecord.disease = this.disease;
        historyRecord.symptom = this.symptom;
        historyRecord.doctor = this.doctor;
        historyRecord.modified = this.modified;
        return editHistory(historyRecord);
    }

    public boolean deleteStore() {
        historyRecord = new History();
        historyRecord.id = this.id;
        return deleteHistory(historyRecord);
    }

    public boolean insertHistory(History obj) {
        return obj.insert();
    }

    public boolean editHistory(History obj) {
        return obj.edit();
    }

    public boolean deleteHistory(History obj) {
        return obj.delete(obj.id);
    }

    public History getHistoryRecord(int id) {
        try {
            cursor = db.rawQuery("SELECT * FROM History WHERE id=?", new String[] {String.valueOf(id)});
            if (cursor.moveToNext()) {
                historyRecord = new History(cursor);
            }
            cursor.close();
        } catch (Exception e){
            historyRecord = null;
        } finally {
            db.close();
        }
        return historyRecord;
    }

    public List<History> getHistoryRecords() {
        items = new ArrayList<>();
        try {
            cursor = db.rawQuery("SELECT * FROM History ORDER BY id", null);
            while (cursor.moveToNext()) {
                items.add(new History(cursor));
            }
            cursor.close();
        } catch (Exception e){
            historyRecord = null;
        } finally {
            db.close();
        }
        return items;
    }

    public List<History> getHistoryRecords(int nStart, int nSize) {
        items = new ArrayList<>();
        try {
            cursor = db.rawQuery("SELECT * FROM History ORDER BY id LIMIT ?,?", new String[] {String.valueOf(nStart), String.valueOf(nSize)});
            while (cursor.moveToNext()) {
                items.add(new History(cursor));
            }
            cursor.close();
        } catch (Exception e){
            historyRecord = null;
        } finally {
            db.close();
        }
        return items;
    }

    public List<History> getHistoryRecords(String column, String value, int nStart, int nSize) {
        String sql = "";
        String searchingValue = "%" + value + "%";
        items = new ArrayList<>();
        try {
            if (column.equals("")) {
                sql = "SELECT * FROM History WHERE patient LIKE '"
                        + searchingValue + "' OR disease LIKE '"
                        + searchingValue + "' OR symptom LIKE '"
                        + searchingValue + "' OR doctor LIKE '"
                        + searchingValue + "' OR modified LIKE '"
                        + searchingValue + "') LIMIT ?,?";
            } else {
                sql = "SELECT * FROM History WHERE "
                        + column + " LIKE '" + searchingValue
                        + "' LIMIT ?,?";
            }
            cursor = db.rawQuery(sql, new String[] {String.valueOf(nStart), String.valueOf(nSize)});
            while (cursor.moveToNext()) {
                items.add(new History(cursor));
            }
            cursor.close();
        } catch (Exception e){
            historyRecord = null;
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

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }
}