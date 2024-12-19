package com.example.aid_assistant_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Doctor_login extends AppCompatActivity {

    EditText getBMDC,getPass;
    Button Login;
    TextView signup;
    Doctor_Database login_test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_login);
        getSupportActionBar().hide();
        signup=findViewById(R.id.sinup_d_t);
        getBMDC=findViewById(R.id.bmdc_d);
        getPass=findViewById(R.id.Password_d);
        Login=findViewById(R.id.Login_button_d);

        login_test=new Doctor_Database(this);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String bmdc=getBMDC.getText().toString();
                String pass=getPass.getText().toString();

                if(login_test.Check_BMDC_Pass(bmdc,pass)) {
                    Toast.makeText(Doctor_login.this,"Login Successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),Doctor_home.class));
                }
                else {
                    Toast.makeText(Doctor_login.this,"Invalid", Toast.LENGTH_SHORT).show();
                }
            }
        });



        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Signup_doctor.class));
            }
        });
    }
}