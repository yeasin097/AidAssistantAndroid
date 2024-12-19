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

import java.util.Calendar;

public class Signup_doctor extends AppCompatActivity {

    EditText Name, Pass, RePass, Email,Phone, BMDC, V_code, Special,ADD;
    Button submit;

    TextView tvDate;

    DatePickerDialog.OnDateSetListener setListener;

    Spinner Gender;

    Doctor_DB bmdc_check_obj;
    Doctor_Database doc_info_obj;
    String date;
    String[] genders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_doctor);
        getSupportActionBar().hide();

        Name=findViewById(R.id.doctor_signupname);
        Pass=findViewById(R.id.doctor_signuppassword);
        RePass=findViewById(R.id.doctor_signuprepassword);
        Email=findViewById(R.id.doctor_signupemail);
        Phone=findViewById(R.id.doctor_signupPhone);
        BMDC=findViewById(R.id.doctor_signupBMDC);
        V_code=findViewById(R.id.doctor_signupverify);
        Special=findViewById(R.id.doctor_signupSpecial);
        //DOB=findViewById(R.id.doctor_signupDOB);
        ADD=findViewById(R.id.doctor_signupAddress);
        submit=findViewById(R.id.doctor_signupsubmit);
        Gender=findViewById(R.id.doctor_signupgender);

        bmdc_check_obj = new Doctor_DB(this);
        doc_info_obj=new Doctor_Database(this);

        tvDate=findViewById(R.id.doctor_signupDOB);

        genders=getResources().getStringArray(R.array.gender);
        ArrayAdapter<CharSequence> adapter=new ArrayAdapter<CharSequence>(this,R.layout.spinner_size,genders);
        //ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.gender, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Gender.setAdapter(adapter);

        Calendar calendar=Calendar.getInstance();
        final int year=calendar.get(Calendar.YEAR);
        final int month=calendar.get(Calendar.MONTH);
        final int day=calendar.get(Calendar.DAY_OF_MONTH);

        tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(
                        Signup_doctor.this, android.R.style.Theme_Holo_Light_Dialog,setListener,year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        setListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOFMonth) {
                month=month+1;
                date=dayOFMonth+"/"+month+"/"+year;
                tvDate.setText(date);
            }
        };

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=Name.getText().toString();
                String pass=Pass.getText().toString();
                String repass=RePass.getText().toString();
                String email=Email.getText().toString();
                String phone=Phone.getText().toString();
                String bmdc=BMDC.getText().toString();
                String v_code=V_code.getText().toString();
                String special=Special.getText().toString();
                //String dob=DOB.getText().toString();
                String address=Name.getText().toString();
                String selected_gender=Gender.getSelectedItem().toString();

                if(bmdc_check_obj.check_BMDC_code(bmdc,v_code)) {
                    doc_info_obj.Insert_Info(bmdc, name, phone,address,special,date,selected_gender, email, pass);
                    Toast.makeText(Signup_doctor.this,"Registration Successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),Doctor_login.class));
                }
                else {
                    Toast.makeText(Signup_doctor.this,"Verifying code doesn't match with BMDC", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}