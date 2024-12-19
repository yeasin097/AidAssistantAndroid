package com.example.aid_assistant_3;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ListView;

public class PresctiptionList extends AppCompatActivity {
    ListView listView_1;
    String[] Drname;
    String[] time;
    String[] Disname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presctiption_list);

        Drname=getResources().getStringArray(R.array.Disease);
        time=getResources().getStringArray(R.array.Disease);
        Disname=getResources().getStringArray(R.array.Disease);

        listView_1=findViewById(R.id.prescription_List);

        //Adapter_medicine adapter_medicine= new Adapter_medicine(this, name, time);
        //listView.setAdapter(adapter_medicine);

        Adapter_prescription adapter_prescription=new Adapter_prescription(this, Disname, Drname, time);

        listView_1.setAdapter(adapter_prescription);


    }
}