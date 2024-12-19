package com.example.aid_assistant_3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQlite extends SQLiteOpenHelper {

    public static final String DBname="Users.db";

    public SQlite(Context context) {
        super(context,DBname,null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase Database) {
        Database.execSQL("create Table Users(email TEXT primary key, name TEXT,  password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase Database, int i, int i1) {
        Database.execSQL("drop Table if exists Users");
    }

    public Boolean insertData(String email, String name, String password){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("name", name);
        contentValues.put("password", password);
        long result = database.insert("Users", null, contentValues);
        if(result==-1) return  false; // insertion failed
        return true; // successful
    }

    public Boolean checkEmail(String email) {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select * from Users where email = ?", new String[]{email});
        if(cursor.getCount()>0) {
            return true;
        }
        return  false;
    }

    public Boolean checkEmailPassword(String email, String password) {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select * from Users where email = ? and password = ?", new String[] {email,password});
        if(cursor.getCount()>0) return  true;
        return  false;
    }
}
