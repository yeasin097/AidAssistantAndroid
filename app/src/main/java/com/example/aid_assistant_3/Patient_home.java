package com.example.aid_assistant_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Patient_home extends AppCompatActivity {
    ImageButton medicine_list;
    ImageButton logout;
    ImageButton prescription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_home);
        getSupportActionBar().hide();

        medicine_list=findViewById(R.id.image_b1);
        logout=findViewById(R.id.image_b6);
        prescription=findViewById(R.id.image_b2);

        medicine_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Patient_home.this,"Ongoing Medicine List", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),Medicine_ongoing.class));
            }
        });

        prescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Patient_home.this,"Prescription List", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),PresctiptionList.class));
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Patient_home.this,"Log out Successfull", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),Patient_login.class));
            }
        });





//        buttons.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Calendar calendar=Calendar.getInstance();
//                DateFormat formatter=new SimpleDateFormat("MM");
//                String datess=formatter.format(calendar.getTime());
//                t_view.setText(datess);
//            }
//        });

    }

    public void onBackPressed() {
        finishAffinity();
    }

}