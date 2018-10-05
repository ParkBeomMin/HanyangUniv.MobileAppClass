package com.example.park.myapplication;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Park on 2017-04-27.
 */

public class GridAdapter extends BaseAdapter {
    ArrayList<Fruit> fruit;
    Context context;

    public GridAdapter(Context context, ArrayList<Fruit> fruit) {
        this.context = context;
        this.fruit = fruit;
    }

    @Override
    public int getCount() {
        return fruit.size();
    }

    @Override
    public Object getItem(int i) {
        return fruit.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
//        if (view == null) {
//            view = LayoutInflater.from(context).inflate(R.layout.griditem, null);
//            final TextView tv = (TextView) view.findViewById(R.id.ivname);
//            final ImageView image = (ImageView) view.findViewById(R.id.img1);
//
//            tv.setText(fruit.get(i).name);
//            image.setImageResource(fruit.get(i).imgno);
//        }
        if (view == null)
            view = new GridItem(context);


        ((GridItem) view).setData(fruit.get(i));
        return view;
    }

    public void addFruit(Fruit one) {
        Log.d(FRUITDATA.LOG_TAG, one.getImgno()+"");
        fruit.add(one);
    }

    public void refresh() {
        this.notifyDataSetChanged();
    }
}
