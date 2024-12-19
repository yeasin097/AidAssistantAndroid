package com.example.aid_assistant_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Patient_login extends AppCompatActivity {

    EditText email,password;
    Button button_login;
    //SQlite dbase;
    Patient_Database dbase;
    TextView signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_login);

        getSupportActionBar().hide();

        email=findViewById(R.id.Email_p);
        password=findViewById(R.id.Password_p);
        button_login=findViewById(R.id.Login_button_p);
        signup=findViewById(R.id.signup_p_t);
        //dbase=new SQlite(this);
        dbase=new Patient_Database(this);

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email=email.getText().toString();
                String Pass=password.getText().toString();

                if(Email.equals("")||Pass.equals("")) {
                    Toast.makeText(Patient_login.this,"Please Enter All The Fields", Toast.LENGTH_SHORT).show();
                }
                else {
                    Boolean checkexist=dbase.checkEmailPassword(Email,Pass);
                    Boolean checkEmail=dbase.checkEmail(Email);
                    if(checkEmail==false) {
                        Toast.makeText(Patient_login.this,"Account doesn't exist", Toast.LENGTH_SHORT).show();
                    }
                    else if(checkexist==true) {
                        Toast.makeText(Patient_login.this,"Sign in Successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),Patient_home.class));
                    }
                    else {
                        Toast.makeText(Patient_login.this,"Wrong Password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Patient_signup.class));
            }
        });


    }
    public void onBackPressed() {
        finishAffinity();
    }


}