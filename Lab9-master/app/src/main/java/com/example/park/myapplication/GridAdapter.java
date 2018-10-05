package com.example.park.myapplication;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

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
        if (view == null) {
            view = new GridItem(context);
        }
        ((GridItem) view).setData(fruit.get(i));
        TextView tv = (TextView) view.findViewById(R.id.tvprice);
        Fruit one = fruit.get(i);
        int op = one.getChecked();
        Log.d("Park", op + "zz");
        if (op == 1) {
            tv.setVisibility(View.VISIBLE);
        } else {
            tv.setVisibility(View.GONE);
        }

        return view;
    }

    public void addFruit(Fruit one) {
        Log.d(FRUITDATA.LOG_TAG, one.getImgno() + "");
        fruit.add(one);
    }

    public void refresh() {
        this.notifyDataSetChanged();
    }
}
