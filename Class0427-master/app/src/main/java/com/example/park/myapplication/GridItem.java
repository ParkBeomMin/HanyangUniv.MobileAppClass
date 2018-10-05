package com.example.park.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Park on 2017-04-27.
 */

public class GridItem extends LinearLayout {
    TextView tv;
    ImageView image;

    public GridItem(Context context) {
        super(context);
        init(context);
    }

    public void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.griditem, this);
        tv = (TextView) findViewById(R.id.ivname);
        image = (ImageView) findViewById(R.id.img1);
    }

    public void setData(Fruit one) {
        tv.setText(one.getName());
        image.setImageResource(one.getImgno());
    }
}
