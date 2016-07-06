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
 * Model       : Role.java
 * Date        : 2016
 * Version     : 1.00
 * Author      : Matanmi Falana
 * Copyright (c) 2016
 */

public class Role extends Activity {

    private String id;
    private String name;
    private String desc;
    private SQLiteStatement stmt = null;
    private SQLiteDatabase db = null;
    private Role roleRecord;
    private Cursor cursor;
    private List<Role> items = null;
    private long numberOfRowAffected = 0;
    private static com.matanmi.project.database.table.Role roleTable;

    public Role() {
        id = name = desc = "";
        db = DatabaseHelper.getDatabase();
    }

    public Role(Cursor rs) throws SQLException {
        id = rs.getString(roleTable.ID_ROLE_ID);
        name = rs.getString(roleTable.ID_ROLE_NAME);
        desc = rs.getString(roleTable.ID_ROLE_DESC);
    }

    public boolean insert() {
        try {
            stmt = db.compileStatement(roleTable.INSERT_ROLE);
            stmt.bindString(1, id);
            stmt.bindString(2, name);
            stmt.bindString(3, desc);
            numberOfRowAffected = stmt.executeInsert();
            stmt.clearBindings();
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
            stmt = db.compileStatement(roleTable.UPDATE_ROLE);
            stmt.bindString(1, name);
            stmt.bindString(2, desc);
            stmt.bindString(10, id);
            numberOfRowAffected = stmt.executeUpdateDelete();
            stmt.clearBindings();
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
            stmt = db.compileStatement(roleTable.DELETE_ROLE);
            stmt.bindString(1, id);
            numberOfRowAffected = stmt.executeUpdateDelete();
            stmt.clearBindings();
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
        roleRecord = new Role();
        roleRecord.id = this.id;
        roleRecord.name = this.name;
        roleRecord.desc = this.desc;
        return insertRole(roleRecord);
    }

    public boolean editStore() {
        roleRecord = new Role();
        roleRecord.id = this.id;
        roleRecord.name = this.name;
        roleRecord.desc = this.desc;
        return editRole(roleRecord);
    }

    public boolean deleteStore() {
        roleRecord = new Role();
        roleRecord.id = this.id;
        return deleteRole(roleRecord);
    }

    public boolean insertRole(Role obj) {
        return obj.insert();
    }

    public boolean editRole(Role obj) {
        return obj.edit();
    }

    public boolean deleteRole(Role obj) {
        return obj.delete(obj.id);
    }

    public boolean isRoleExist(String param) {
        boolean userExist = false;
        try {
            cursor = db.rawQuery("SELECT * FROM ROLE WHERE id=?", new String[] {param});
            if (cursor.moveToNext()) {
                userExist = true;
            }
            cursor.close();
        } catch (Exception e){
            userExist = false;
        }
        return userExist;
    }

    public Role getRoleRecord(String id) {
        try {
            cursor = db.rawQuery("SELECT * FROM ROLE WHERE id=?", new String[] {id});
            if (cursor.moveToNext()) {
                roleRecord = new Role(cursor);
            }
            cursor.close();
        } catch (Exception e){
            roleRecord = null;
        }
        return roleRecord;
    }

    public List<String> getRoleList() {
        List<String> items = new ArrayList<>();
        try {
            cursor = db.rawQuery("SELECT * FROM ROLE ORDER BY id", null);
            while (cursor.moveToNext()) {
                items.add(cursor.getString(roleTable.ID_ROLE_NAME));
            }
            cursor.close();
        } catch (Exception e){
            roleRecord = null;
        }
        return items;
    }

    public List<Role> getRoleRecords() {
        items = new ArrayList<>();
        try {
            cursor = db.rawQuery("SELECT * FROM ROLE ORDER BY id", null);
            while (cursor.moveToNext()) {
                items.add(new Role(cursor));
            }
            cursor.close();
        } catch (Exception e) {
            roleRecord = null;
        }
        return items;
    }

    public List<Role> getRoleRecords(int nStart, int nSize) {
        items = new ArrayList<>();
        try {
            cursor = db.rawQuery("SELECT * FROM ROLE ORDER BY id LIMIT ?,?", new String[] {String.valueOf(nStart), String.valueOf(nSize)});
            while (cursor.moveToNext()) {
                items.add(new Role(cursor));
            }
            cursor.close();
        } catch (Exception e) {
            roleRecord = null;
        }
        return items;
    }

    public List<Role> getRoleRecords(String column, String value, int nStart, int nSize) {
        String sql = "";
        String searchingValue = "%" + value + "%";
        items = new ArrayList<>();
        try {
            if (column.equals("")) {
                sql = "SELECT * FROM ROLE WHERE name LIKE '"
                        + searchingValue + "' OR desc LIKE '"
                        + searchingValue + "') LIMIT ?,?";
            } else {
                sql = "SELECT * FROM ROLE WHERE "
                        + column + " LIKE '" + searchingValue
                        + "' LIMIT ?,?";
            }
            cursor = db.rawQuery(sql, new String[] {String.valueOf(nStart), String.valueOf(nSize)});
            while (cursor.moveToNext()) {
                items.add(new Role(cursor));
            }
            cursor.close();
        } catch (Exception e) {
            roleRecord = null;
        }
        return items;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}