package com.example.aid_assistant_3;

import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class Patient_Database extends SQLiteOpenHelper {
    public static final String Patient_Dbase="Patient_Database.db";

    public Patient_Database( Context context) {
        super(context, Patient_Dbase, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase Database) {
        Database.execSQL("create Table Patient_Database(Patient_ID TEXT primary key,Name TEXT," +
                " Mobile TEXT, Address TEXT, Date_of_Birth TEXT, Email TEXT,Password TEXT," +
                " Blood_Group TEXT, Gender TEXT ,Allergies TEXT," +
                " Age TEXT, BMI TEXT)");

    }
    @Override
    public void onUpgrade(SQLiteDatabase Database, int i, int i1) {
        Database.execSQL("drop Table if exists Patient_Database");
    }


    Boolean Insert_Info(String name, String mobile, String adrs, String dob, String email,
                        String pass,String bgroup, String gender,String height, String weight, String allergies, String age) {

        SQLiteDatabase patient_db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();

        long user_counter= DatabaseUtils.queryNumEntries(patient_db,"Patient_Database");

        double H=Double.parseDouble(height)/100; double W=(Double.parseDouble(weight)); double BMI=W/(H*H);
        Log.d(TAG, Double.toString(BMI));
        contentValues.put("Patient_ID", Long.toString(user_counter+1));
        contentValues.put("Name", name);
        contentValues.put("Mobile", mobile);
        contentValues.put("Address", adrs);
        contentValues.put("Date_of_Birth", dob);
        contentValues.put("Email", email);
        contentValues.put("Password", pass);
        contentValues.put("Blood_Group", bgroup);
        contentValues.put("Gender", gender);
        contentValues.put("Allergies", allergies);
        contentValues.put("Age", age);
        contentValues.put("BMI", Double.toString(BMI));
        long succ=patient_db.insert("Patient_Database", null, contentValues);
        if(succ==-1) return false;
        return  true;
    }

    public Boolean checkEmailPassword(String email, String password) {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select * from Patient_Database where email = ? and password = ?", new String[] {email,password});
        if(cursor.getCount()>0) return  true;
        return  false;
    }

    public Boolean checkEmail(String email) {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select * from Patient_Database where email = ?", new String[]{email});
        if(cursor.getCount()>0) {
            return true;
        }
        return  false;
    }
}
