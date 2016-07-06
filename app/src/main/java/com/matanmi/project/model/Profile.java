package com.matanmi.project.model;

import android.app.Activity;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.matanmi.project.database.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class Profile extends Activity {

    private int id;
    public String name;
    public String email;
    public String mobile;
    public int age;
    public String gender;
    public String role;
    public String password;
    private SQLiteStatement stmt = null;
    private SQLiteDatabase db = null;
    private Profile profileRecord;
    private Cursor cursor;
    private List<Profile> items = null;
    private long numberOfRowAffected = 0;
    private static com.matanmi.project.database.table.Profile profileTable;

    public Profile() {
        name = email = mobile = "";
        gender = role = password = "";
        id = age = 0;
        db = DatabaseHelper.getDatabase();
    }

    public Profile(Cursor rs) throws SQLException {
        id = rs.getInt(profileTable.ID_PROFILE_ID);
        name = rs.getString(profileTable.ID_PROFILE_NAME);
        email = rs.getString(profileTable.ID_PROFILE_EMAIL);
        mobile = rs.getString(profileTable.ID_PROFILE_MOBILE);
        age = rs.getInt(profileTable.ID_PROFILE_AGE);
        gender = rs.getString(profileTable.ID_PROFILE_GENDER);
        role = rs.getString(profileTable.ID_PROFILE_ROLE);
        password = rs.getString(profileTable.ID_PROFILE_PASSWORD);
    }

    public boolean insert() {
        try {
            stmt = db.compileStatement(profileTable.INSERT_PROFILE);
            stmt.bindString(1, name);
            stmt.bindString(2, email);
            stmt.bindString(3, mobile);
            stmt.bindLong(4, age);
            stmt.bindString(5, gender);
            stmt.bindString(6, role);
            stmt.bindString(7, password);
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
            stmt = db.compileStatement(profileTable.UPDATE_PROFILE);
            stmt.bindString(1, name);
            stmt.bindString(2, email);
            stmt.bindString(3, mobile);
            stmt.bindLong(4, age);
            stmt.bindString(5, gender);
            stmt.bindString(6, role);
            stmt.bindString(7, password);
            stmt.bindLong(8, id);
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

    public boolean delete(int id) {
        try {
            stmt = db.compileStatement(profileTable.DELETE_PROFILE);
            stmt.bindLong(1, id);
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
        profileRecord = new Profile();
        profileRecord.name = this.name;
        profileRecord.email = this.email;
        profileRecord.mobile = this.mobile;
        profileRecord.age = this.age;
        profileRecord.gender = this.gender;
        profileRecord.role = this.role;
        profileRecord.password = this.password;
        return insertProfile(profileRecord);
    }

    public boolean editStore() {
        profileRecord = new Profile();
        profileRecord.id = this.id;
        profileRecord.name = this.name;
        profileRecord.email = this.email;
        profileRecord.mobile = this.mobile;
        profileRecord.age = this.age;
        profileRecord.gender = this.gender;
        profileRecord.role = this.role;
        profileRecord.password = this.password;
        return editProfile(profileRecord);
    }

    public boolean deleteStore() {
        profileRecord = new Profile();
        profileRecord.id = this.id;
        return deleteProfile(profileRecord);
    }

    public boolean insertProfile(Profile obj) {
        return obj.insert();
    }

    public boolean editProfile(Profile obj) {
        return obj.edit();
    }

    public boolean deleteProfile(Profile obj) {
        return obj.delete(obj.id);
    }

    public boolean isProfileExist(String param) {
        boolean profileExist = false;
        try {
            cursor = db.rawQuery("SELECT * FROM PROFILE WHERE email=? OR mobile=?", new String[] {param, param});
            if (cursor.moveToNext()) {
                profileExist = true;
            }
            cursor.close();
        } catch (Exception e) {
            profileExist = false;
        }
        return profileExist;
    }

    public boolean isProfileExist(Profile profile) {
        boolean profileExist = false;
        try {
            cursor = db.rawQuery("SELECT * FROM PROFILE WHERE email=? OR mobile=?", new String[] {profile.email, profile.mobile});
            if (cursor.moveToNext()) {
                profileExist = true;
            }
            cursor.close();
        } catch (Exception e) {
            profileExist = false;
        }
        return profileExist;
    }

    public Profile getProfileRecord(int id) {
        try {
            cursor = db.rawQuery("SELECT * FROM PROFILE WHERE id=?", new String[] {String.valueOf(id)});
            if (cursor.moveToNext()) {
                profileRecord = new Profile(cursor);
            }
            cursor.close();
        } catch (Exception e){
            profileRecord = null;
        }
        return profileRecord;
    }

    public Profile getProfileRecord(String param) {
        try {
            cursor = db.rawQuery("SELECT * FROM PROFILE WHERE email=? OR mobile=?", new String[] {param, param});
            if (cursor.moveToNext()) {
                profileRecord = new Profile(cursor);
            }
            cursor.close();
        } catch (Exception e){
            profileRecord = null;
        }
        return profileRecord;
    }

    public List<Profile> getProfileRecords() {
        items = new ArrayList<>();
        try {
            cursor = db.rawQuery("SELECT * FROM PROFILE ORDER BY id", null);
            while (cursor.moveToNext()) {
                items.add(new Profile(cursor));
            }
            cursor.close();
        } catch (Exception e){
            profileRecord = null;
        }
        return items;
    }

    public List<Profile> getProfileRecords(int nStart, int nSize) {
        items = new ArrayList<>();
        try {
            cursor = db.rawQuery("SELECT * FROM PROFILE ORDER BY id LIMIT ?,?", new String[] {String.valueOf(nStart), String.valueOf(nSize)});
            while (cursor.moveToNext()) {
                items.add(new Profile(cursor));
            }
            cursor.close();
        } catch (Exception e){
            profileRecord = null;
        }
        return items;
    }

    public List<Profile> getProfileRecords(String column, String value, int nStart, int nSize) {
        String sql = "";
        String searchingValue = "%" + value + "%";
        items = new ArrayList<>();
        try {
            if (column.equals("")) {
                sql = "SELECT * FROM PROFILE WHERE name LIKE '"
                        + searchingValue + "' OR email LIKE '"
                        + searchingValue + "' OR mobile LIKE '"
                        + searchingValue + "' OR age LIKE '"
                        + searchingValue + "' OR gender LIKE '"
                        + searchingValue + "' OR role LIKE '"
                        + searchingValue + "') LIMIT ?,?";
            } else {
                sql = "SELECT * FROM PROFILE WHERE "
                        + column + " LIKE '" + searchingValue
                        + "' LIMIT ?,?";
            }
            cursor = db.rawQuery(sql, new String[] {String.valueOf(nStart), String.valueOf(nSize)});
            while (cursor.moveToNext()) {
                items.add(new Profile(cursor));
            }
            cursor.close();
        } catch (Exception e){
            profileRecord = null;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}