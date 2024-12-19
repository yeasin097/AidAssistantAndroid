package com.example.aid_assistant_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Profile_Selection extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_selection);
        getSupportActionBar().hide();

        Button Doctor_b=findViewById(R.id.select_doctor);
        Button Patient_b=findViewById(R.id.select_patient);

        Doctor_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Doctor_login.class));
            }
        });

        Patient_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(getApplicationContext(),Patient_login.class));
                startActivity(new Intent(getApplicationContext(),Patient_home.class));
            }
        });


    }
    public void onBackPressed() {
        finishAffinity();
    }

}