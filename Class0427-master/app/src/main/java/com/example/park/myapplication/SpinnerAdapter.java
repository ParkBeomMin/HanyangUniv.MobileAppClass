package com.example.park.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Park on 2017-04-27.
 */

public class SpinnerAdapter extends BaseAdapter {
    String fruit[];
    Context context;


    public  SpinnerAdapter(String fruit[], Context context) {
        this.fruit = fruit;
        this.context = context;
    }
    @Override
    public int getCount() {
        return fruit.length;
    }

    @Override
    public Object getItem(int i) {
        return fruit[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.spinner_item, null);

            final TextView tv = (TextView)view.findViewById(R.id.tvname);
            tv.setText(fruit[i]);

            final CheckBox c1 = (CheckBox) view.findViewById(R.id.checkbox);
            c1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    String msg = "선택되었습니다";
                    if(!b) {
                        msg = "선택되지않았습니다";
                    }
                    Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
                }
            });

        }
        return view;
    }
}
