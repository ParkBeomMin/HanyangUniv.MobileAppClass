package com.example.park.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Park on 2017-04-27.
 */

public class GridItem extends LinearLayout{
    TextView tv1, tv2;
    ImageView image;

    public GridItem(Context context) {
        super(context);
        init(context);
    }

    public void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.grid_item, this);
        tv1 = (TextView) findViewById(R.id.tvname);
        tv2 = (TextView) findViewById(R.id.tvprice);
        image = (ImageView) findViewById(R.id.img);
    }

    public void setData(Fruit one) {
        tv1.setText(one.getName());
        image.setImageResource(one.getImgno());
        tv2.setText(one.getPrice()+"원");
    }
}
