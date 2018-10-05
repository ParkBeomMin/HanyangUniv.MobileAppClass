package com.example.park.myapplication;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by Park on 2017-04-27.
 */

public class AddFruit extends LinearLayout implements View.OnClickListener {
    int imageno = 0;
    AutoCompleteTextView et;
    ImageView img;
    Button b_add, b_next;

    public AddFruit(Context context, AttributeSet attrs) { //xml에서 쓸수있음
        super(context, attrs);
        init(context);
    }

    void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.fruitadd, this);
        et = (AutoCompleteTextView) findViewById(R.id.f_name);
        img = (ImageView) findViewById(R.id.image1);
        b_add = (Button) findViewById(R.id.b_add);
        b_next = (Button) findViewById(R.id.b_next);
        b_add.setOnClickListener(this);
        b_next.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view == b_add) {
            onAddListener.onAdd(et.getText().toString(), imageno);
        } else {
            if (imageno == FRUITDATA.imglist.length - 1) imageno = -1;
            img.setImageResource(FRUITDATA.imglist[++imageno]);
        }
    }

    interface OnAddListener {
        void onAdd(String name, int imageno);
    }

    public OnAddListener onAddListener;

    public void setOnAddListener(OnAddListener onAddListener) {
        this.onAddListener = onAddListener;
    }
}
