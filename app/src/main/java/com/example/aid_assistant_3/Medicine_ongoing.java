package com.example.aid_assistant_3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Medicine_ongoing extends AppCompatActivity {

    ListView listView;
    String[] name;
    String[] time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_ongoing);
        getSupportActionBar().hide();
        listView=findViewById(R.id.list_view_medicine);

        name=getResources().getStringArray(R.array.Medicine_names);
        time=getResources().getStringArray(R.array.Medicine_times);

        //ArrayAdapter<String> adapter= new ArrayAdapter<>(this,R.layout.sample_medicine_list, R.id.med_name, bd);
        //listView.setAdapter(adapter);

        Adapter_medicine adapter_medicine= new Adapter_medicine(this, name, time);
        listView.setAdapter(adapter_medicine);
    }
}