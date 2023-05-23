package com.example.loginpage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "UserInfo.db";
    private static final String TABLE_NAME = "user_info";
    private static final String COL_USERNAME = "username";
    private static final String COL_FIRST_NAME = "first_name";
    private static final String COL_LAST_NAME = "last_name";
    private static final String COL_PASSWORD = "password";
    private static final String COL_PHONE_NUMBER = "phone_number";
    private static final String COL_BIRTH_DATE = "birth_date";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_USERNAME + " TEXT PRIMARY KEY, " +
                COL_FIRST_NAME + " TEXT, " +
                COL_LAST_NAME + " TEXT, " +
                COL_PASSWORD + " TEXT, " +
                COL_PHONE_NUMBER + " TEXT, " +
                COL_BIRTH_DATE + " TEXT)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String username, String firstName, String lastName, String password,
                              String phoneNumber, String birthDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_USERNAME, username);
        contentValues.put(COL_FIRST_NAME, firstName);
        contentValues.put(COL_LAST_NAME, lastName);
        contentValues.put(COL_PASSWORD, password);
        contentValues.put(COL_PHONE_NUMBER, phoneNumber);
        contentValues.put(COL_BIRTH_DATE, birthDate);
        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1;
    }

    public boolean updateData(String username, String firstName, String lastName, String password,
                              String phoneNumber, String birthDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_FIRST_NAME, firstName);
        contentValues.put(COL_LAST_NAME, lastName);
        contentValues.put(COL_PASSWORD, password);
        contentValues.put(COL_PHONE_NUMBER, phoneNumber);
        contentValues.put(COL_BIRTH_DATE, birthDate);
        int rowsAffected = db.update(TABLE_NAME, contentValues, COL_USERNAME + " = ?", new String[]{username});
        return rowsAffected > 0;
    }

    public Cursor getUserData(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = {COL_USERNAME, COL_FIRST_NAME, COL_LAST_NAME, COL_PASSWORD, COL_PHONE_NUMBER, COL_BIRTH_DATE};
        String selection = COL_USERNAME + " = ?";
        String[] selectionArgs = {username};
        return db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
    }

    public boolean checkUserCredentials(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = {COL_USERNAME};
        String selection = COL_USERNAME + " = ? AND " + COL_PASSWORD + " = ?";
        String[] selectionArgs = {username, password};
        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        boolean userExists = cursor.getCount() > 0;
        cursor.close();
        return userExists;
    }
}







