package com.matanmi.project.model;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.matanmi.project.database.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class Disease extends com.matanmi.project.database.table.Disease {

    public int id;
    public String name;
    public String type;
    public String desc;
    private SQLiteStatement stmt = null;
    private SQLiteDatabase db = null;
    private Disease diseaseRecord;
    private Cursor cursor;
    private List<Disease> items = null;
    private long numberOfRowAffected = 0;

    public Disease() {
        name = type = desc = "";
        id = 0;
        db = DatabaseHelper.getDatabase();
    }

    public Disease(Cursor rs) throws SQLException {
        id = rs.getInt(ID_DISEASE_ID);
        name = rs.getString(ID_DISEASE_NAME);
        type = rs.getString(ID_DISEASE_TYPE);
        desc = rs.getString(ID_DISEASE_DESC);
    }

    public boolean insert() {
        try {
            stmt = db.compileStatement(INSERT_DISEASE);
            stmt.bindString(1, name);
            stmt.bindString(2, type);
            stmt.bindString(3, desc);
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
            stmt = db.compileStatement(UPDATE_DISEASE);
            stmt.bindString(1, name);
            stmt.bindString(2, type);
            stmt.bindString(3, desc);
            stmt.bindLong(4, id);
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
            stmt = db.compileStatement(DELETE_DISEASE);
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
        diseaseRecord = new Disease();
        diseaseRecord.name = this.name;
        diseaseRecord.type = this.type;
        diseaseRecord.desc = this.desc;
        return insertDisease(diseaseRecord);
    }

    public boolean editStore() {
        diseaseRecord = new Disease();
        diseaseRecord.id = this.id;
        diseaseRecord.name = this.name;
        diseaseRecord.type = this.type;
        diseaseRecord.desc = this.desc;
        return editDisease(diseaseRecord);
    }

    public boolean deleteStore() {
        diseaseRecord = new Disease();
        diseaseRecord.id = this.id;
        return deleteDisease(diseaseRecord);
    }

    public boolean insertDisease(Disease obj) {
        return obj.insert();
    }

    public boolean editDisease(Disease obj) {
        return obj.edit();
    }

    public boolean deleteDisease(Disease obj) {
        return obj.delete(obj.id);
    }

    public boolean isDiseaseExist(String param) {
        boolean diseaseExist = false;
        try {
            cursor = db.rawQuery("SELECT * FROM DISEASE WHERE name=?", new String[] {param});
            if (cursor.moveToNext()) {
                diseaseExist = true;
            }
            cursor.close();
        } catch (Exception e){
            diseaseExist = false;
        } finally {
            db.close();
        }
        return diseaseExist;
    }

    public Disease getDiseaseRecord(int id) {
        try {
            cursor = db.rawQuery("SELECT * FROM DISEASE WHERE id=?", new String[] {String.valueOf(id)});
            if (cursor.moveToNext()) {
                diseaseRecord = new Disease(cursor);
            }
            cursor.close();
        } catch (Exception e){
            diseaseRecord = null;
        } finally {
            db.close();
        }
        return diseaseRecord;
    }

    public List<Disease> getDiseaseRecords() {
        items = new ArrayList<>();
        try {
            cursor = db.rawQuery("SELECT * FROM DISEASE ORDER BY id", null);
            while (cursor.moveToNext()) {
                items.add(new Disease(cursor));
            }
            cursor.close();
        } catch (Exception e){
            diseaseRecord = null;
        } finally {
            db.close();
        }
        return items;
    }

    public List<Disease> getDiseaseRecords(int nStart, int nSize) {
        items = new ArrayList<>();
        try {
            cursor = db.rawQuery("SELECT * FROM DISEASE ORDER BY id LIMIT ?,?", new String[] {String.valueOf(nStart), String.valueOf(nSize)});
            while (cursor.moveToNext()) {
                items.add(new Disease(cursor));
            }
            cursor.close();
        } catch (Exception e){
            diseaseRecord = null;
        } finally {
            db.close();
        }
        return items;
    }

    public List<Disease> getDiseaseRecords(String column, String value, int nStart, int nSize) {
        String sql = "";
        String searchingValue = "%" + value + "%";
        items = new ArrayList<>();
        try {
            if (column.equals("")) {
                sql = "SELECT * FROM DISEASE WHERE name LIKE '"
                        + searchingValue + "' OR type LIKE '"
                        + searchingValue + "' OR desc LIKE '"
                        + searchingValue + "') LIMIT ?,?";
            } else {
                sql = "SELECT * FROM DISEASE WHERE "
                        + column + " LIKE '" + searchingValue
                        + "' LIMIT ?,?";
            }
            cursor = db.rawQuery(sql, new String[] {String.valueOf(nStart), String.valueOf(nSize)});
            while (cursor.moveToNext()) {
                items.add(new Disease(cursor));
            }
            cursor.close();
        } catch (Exception e){
            diseaseRecord = null;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}