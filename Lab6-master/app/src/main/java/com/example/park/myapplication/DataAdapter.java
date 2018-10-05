package com.example.park.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Park on 2017-04-13.
 */

public class DataAdapter extends BaseAdapter {
    ArrayList<Data> data = new ArrayList<Data>();
    Context context;

    public DataAdapter(Context context, ArrayList<Data> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_layout, null);
        }
        TextView t1 = (TextView) view.findViewById(R.id.name);
        TextView t2 = (TextView) view.findViewById(R.id.tell);
        ImageView i1 = (ImageView) view.findViewById(R.id.imageView);
        CheckBox c1 = (CheckBox) view.findViewById(R.id.checkbox);
        final Data d1 = data.get(i);
        t1.setText(d1.getName());
        t2.setText(d1.getCall());
        int op = d1.getCategory();
        if (op == 0) {
            i1.setImageResource(R.drawable.chicken);
        } else if (op == 1) {
            i1.setImageResource(R.drawable.pizza);
        } else if (op == 2) {
            i1.setImageResource(R.drawable.hamburger);
        }
        int op_1 = d1.getCheck();
        if (op_1 == 0) {
            c1.setVisibility(View.GONE);
        } else {
            c1.setVisibility(View.VISIBLE);
        }
        d1.setV(view);
        return view;
    }

    Comparator<Data> nameAsc = new Comparator<Data>() {
        @Override
        public int compare(Data data, Data t1) {
            return data.getName().compareTo(t1.getName());
        }
    };
    Comparator<Data> nameDesc = new Comparator<Data>() {
        @Override
        public int compare(Data data, Data t1) {
            return t1.getName().compareTo(data.getName());
        }
    };
    Comparator<Data> kindAsc = new Comparator<Data>() {
        @Override
        public int compare(Data data, Data t1) {
            return data.getCategory() - t1.getCategory();
        }
    };
    Comparator<Data> kindDesc = new Comparator<Data>() {
        @Override
        public int compare(Data data, Data t1) {
            return t1.getCategory() - data.getCategory();
        }
    };
    final static int NAME_ASC = 0;
    final static int NAME_DESC = 1;
    final static int KIND_ASC = 2;
    final static int KIND_DESC = 3;

    public void setSort(int sortType) {
        if (sortType == NAME_ASC) {
            Collections.sort(data, nameAsc);
            this.notifyDataSetChanged();
        } else if (sortType == NAME_DESC) {
            Collections.sort(data, nameDesc);
            this.notifyDataSetChanged();
        } else if (sortType == KIND_ASC) {
            Collections.sort(data, kindAsc);
            this.notifyDataSetChanged();
        } else if (sortType == KIND_DESC) {
            Collections.sort(data, kindDesc);
            this.notifyDataSetChanged();
        }
    }
}

