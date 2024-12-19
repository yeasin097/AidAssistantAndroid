package com.example.aid_assistant_3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Patient_signup extends AppCompatActivity {

    EditText name, password, repassword, email, weight, height, allergies, phone, address;
    Button SSubmit;
    TextView tvDate;
    String date;

    //String age;

    DatePickerDialog.OnDateSetListener setListener;

    Spinner Gender,BGroup;
    String[] genders;
    String[] bgroups;
    String Age;

    SQlite dbase; Patient_Database pdbase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_signup);

        getSupportActionBar().hide();
        name=findViewById(R.id.patient_signupname);
        password=findViewById(R.id.patient_signuppassword);
        repassword=findViewById(R.id.patient_signuprepassword);
        email=findViewById(R.id.patient_signupemail);
        SSubmit=findViewById(R.id.patient_signupsubmit);
        phone=findViewById(R.id.patient_signupphone);
        Gender=findViewById(R.id.patient_signupgender);
        BGroup=findViewById(R.id.patient_signupblood);
        weight=findViewById(R.id.patient_signupweight);
        height=findViewById(R.id.patient_signupheight);
        allergies=findViewById(R.id.patient_signupAllergy);
        address=findViewById(R.id.patient_signupaddress);



        dbase=new SQlite(this); pdbase=new Patient_Database(this);

        tvDate=findViewById(R.id.patient_signupDOB);

        Calendar calendar=Calendar.getInstance();
        final int year=calendar.get(Calendar.YEAR);
        final int month=calendar.get(Calendar.MONTH);
        final int day=calendar.get(Calendar.DAY_OF_MONTH);

        //age=getAge(year,month,day);

        genders=getResources().getStringArray(R.array.gender);
        ArrayAdapter<CharSequence> adapter=new ArrayAdapter<CharSequence>(this,R.layout.spinner_size,genders);
        //ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.gender, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Gender.setAdapter(adapter);

        bgroups=getResources().getStringArray(R.array.blood_group);
        ArrayAdapter<CharSequence> adapter_bg=new ArrayAdapter<CharSequence>(this,R.layout.spinner_size,bgroups);
        //ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.gender, android.R.layout.simple_spinner_item);
        adapter_bg.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        BGroup.setAdapter(adapter_bg);



        tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(
                        Patient_signup.this, android.R.style.Theme_Holo_Light_Dialog,setListener,year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        setListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOFMonth) {
                month=month+1;
                date=dayOFMonth+"/"+month+"/"+year;
                Age=getAge(year,month,dayOFMonth);
                tvDate.setText(date);
            }
        };



        SSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Username=name.getText().toString();
                String Pass = password.getText().toString();
                String Repass = repassword.getText().toString();
                String Email = email.getText().toString();
                String selected_gender=Gender.getSelectedItem().toString();
                String selected_bg=BGroup.getSelectedItem().toString();
                //String age=getAge(year,month,day);
                String Weight=weight.getText().toString();
                String Height=height.getText().toString();
                String Allergy=allergies.getText().toString();
                String Phone=phone.getText().toString();
                String adrs=address.getText().toString();

                if(Username.equals("") || Pass.equals("") || Repass.equals("") || Email.equals("")) {
                    Toast.makeText(Patient_signup.this, "Please Enter All The Fileds", Toast.LENGTH_SHORT).show();
                }
                else {
                    if(Pass.equals(Repass)) {
                        /*Boolean checkuseremail = dbase.checkEmail(Email);
                        if(checkuseremail==false){
                            Boolean insert=dbase.insertData(Email,Username,Pass);
                            if(insert==true) {
                                Toast.makeText(Patient_signup.this,"Registered Successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),Patient_home.class));
                            }
                            else {
                                Toast.makeText(Patient_signup.this,"Registration Failed", Toast.LENGTH_SHORT).show();
                            }

                        }
                        else{
                            Toast.makeText(Patient_signup.this,"User Already Exist", Toast.LENGTH_SHORT).show();
                        }*/

                        Boolean insert=pdbase.Insert_Info(Username,Phone,adrs,date,Email, Pass, selected_bg,selected_gender, Height, Weight,Allergy,Age);
                        if(insert) {
                            Toast.makeText(Patient_signup.this,"Registered Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),Patient_login.class));
                        }
                        else {
                            Toast.makeText(Patient_signup.this,"Registration Failed", Toast.LENGTH_SHORT).show();
                        }


                    }
                    else {
                        Toast.makeText(Patient_signup.this,"Passwords doesn't match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }

    public String getAge(int year, int month, int day) {
        Calendar calendar=Calendar.getInstance();
        DateFormat formatter_Y=new SimpleDateFormat("yyyy");
        int y=Integer.parseInt(formatter_Y.format(calendar.getTime()));
        DateFormat formatter_M=new SimpleDateFormat("MM");
        int m=Integer.parseInt(formatter_M.format(calendar.getTime()));
        DateFormat formatter_D=new SimpleDateFormat("dd");
        int d=Integer.parseInt(formatter_D.format(calendar.getTime()));


//        Calendar calendar=Calendar.getInstance();
//        DateFormat formatter=new SimpleDateFormat("MM");
//        String datess=formatter.format(calendar.getTime());


        int days=(y-year-1)*365;
        days=days+(12-month)*30+(30-day);
        days=days+(m-1)*30+d;
        return Integer.toString(days/365);
    }
}