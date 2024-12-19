package com.example.aid_assistant_3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Adapter_medicine extends BaseAdapter {

    String[] med_name;
    String[] med_time;
    Context context;
    LayoutInflater inflater;

    Adapter_medicine(Context context, String[] med_name, String[] med_time) {
        this.context=context;
        this.med_name=med_name;
        this.med_time=med_time;
    }
    @Override
    public int getCount() {
        return med_name.length;
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
        view=inflater.inflate(R.layout.sample_medicine_list, viewGroup, false);
        TextView textView=view.findViewById(R.id.med_name);
        TextView textView1=view.findViewById(R.id.med_time);
        ImageView imageView=view.findViewById(R.id.med_image);

        textView.setText(med_name[i]);
        textView1.setText(med_time[i]);
        if(i%3==0){
            imageView.setImageResource(R.drawable.tablet);
        }
        else if(i%3==1) {
            imageView.setImageResource(R.drawable.capsule);
        }
        else {
            imageView.setImageResource(R.drawable.syrup);
        }
        //imageView.setImageResource(R.drawable.medicine);
        return view;
    }
}
