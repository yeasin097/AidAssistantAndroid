package com.example.aid_assistant_3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Adapter_prescription extends BaseAdapter {

    String[] dr_name;
    String[] date;
    String[] disease_name;
    Context context;
    LayoutInflater inflater;

    Adapter_prescription(Context context,String[] disease_name, String[] dr_name, String[] date){
        this.context=context;
        this.disease_name=disease_name;
        this.dr_name=dr_name;
        this.date=date;
    }
    @Override
    public int getCount() {
        return dr_name.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view=inflater.inflate(R.layout.sample_prescription, viewGroup, false);
        TextView disname=view.findViewById(R.id.dis_name);
        TextView name=view.findViewById(R.id.doc_name);
        TextView Date=view.findViewById(R.id.date_pres);
        TextView serial=view.findViewById(R.id.prescription_serial);

        disname.setText(disease_name[i]);
        name.setText(dr_name[i]);
        Date.setText(date[i]);
        serial.setText(Integer.toString(i+1));
        return view;
    }
}
