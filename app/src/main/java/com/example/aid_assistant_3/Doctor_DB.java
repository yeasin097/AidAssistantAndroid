package com.example.aid_assistant_3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



import java.util.concurrent.ThreadLocalRandom;

public class Doctor_DB extends SQLiteOpenHelper {

    public static final String Doctor_Database = "BMDC_No.db";

    public Doctor_DB( Context context) {
        super(context, Doctor_Database, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase Doc_db) {
        Doc_db.execSQL("create Table BMDC_No(BMDC_NO TEXT primary key,Verify_code TEXT)");
        //creating bmdc table for signup doctor
    }

    @Override
    public void onUpgrade(SQLiteDatabase Doc_db, int i, int i1) {
        Doc_db.execSQL("drop Table if exists BMDC_No");
        // if the table is already created.
    }

    public void push_BMDC() {
        for (int i=1; i<=100; i++) {
            SQLiteDatabase Doc_db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("BMDC_NO", i);
            int k = ThreadLocalRandom.current().nextInt(100000, 1000000);
            contentValues.put("Verify_code",k);
            Doc_db.insert("BMDC_NO",null,contentValues);
        }
//        SQLiteDatabase Doc_db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("BMDC_NO","2000");
//        contentValues.put("Verify_code", "2001");
//        Doc_db.insert("BMDC_No",null, contentValues);
    }

    public Boolean check_BMDC_code(String bmdc, String code) {
        SQLiteDatabase Doc_db = this.getWritableDatabase();
        Cursor cursor=Doc_db.rawQuery("Select * from BMDC_No where BMDC_NO=? and Verify_code=?", new String[]{bmdc,code});
        if(cursor.getCount()>0) return  true;
        else return false;
    }
}
