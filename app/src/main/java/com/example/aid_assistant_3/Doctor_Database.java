package com.example.aid_assistant_3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Doctor_Database extends SQLiteOpenHelper {

    public static final String Doctor_Database = "Doctor_info.db";

    public Doctor_Database( Context context) {
        super(context, Doctor_Database, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase doc_db) {
        doc_db.execSQL("create Table DOCTOR_INFO(BMDC_NO TEXT primary key,Name TEXT, Mobile TEXT, Address TEXT,Speciality TEXT, Date_of_Birth TEXT, Gender TEXT, Email TEXT, Password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase doc_db, int i, int i1) {
        doc_db.execSQL("drop Table if exists DOCTOR_INFO");
    }

    Boolean Insert_Info(String bmdc,String name, String mob, String ad, String spc, String dob, String gen, String email, String pass) {
        SQLiteDatabase Doc_db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("BMDC_NO", bmdc);
        contentValues.put("Name", name);
        contentValues.put("Mobile", mob);
        contentValues.put("Address", ad);
        contentValues.put("Speciality", spc);
        contentValues.put("Date_of_Birth", dob);
        contentValues.put("Gender", gen);
        contentValues.put("Email", email);
        contentValues.put("Password", pass);
        long res=Doc_db.insert("DOCTOR_INFO",null, contentValues);
        if(res==-1) return false;
        return  true;
    }

    Boolean Check_BMDC_Pass(String bmdc, String pass) {
        SQLiteDatabase database=getWritableDatabase();
        Cursor cursor=database.rawQuery("Select * from Doctor_info where BMDC_NO=? and Password=?", new String[]{bmdc,pass});
        if(cursor.getCount()>0) return  true;
        return  false;
    }
}
