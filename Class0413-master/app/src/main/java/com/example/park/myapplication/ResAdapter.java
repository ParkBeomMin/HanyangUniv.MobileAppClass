package com.example.park.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Park on 2017-04-13.
 */

public class ResAdapter extends BaseAdapter {
    ArrayList<ResInfo> data = new ArrayList<ResInfo>();
    Context context;

    public ResAdapter(Context context, ArrayList<ResInfo> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() { //데이터갯수리턴
        return data.size();
    }

    @Override
    public Object getItem(int i) { //현재위치아이템리턴
        return data.get(i);
    }

    @Override
    public long getItemId(int i) { //아이디리턴
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) { //해당위치 view 리턴
//        ViewHolder viewHolder = new ViewHolder();
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.listview_layout, null);

//            viewHolder.t1 =   (TextView) view.findViewById(R.id.tvname);
//            viewHolder.t2 =   (TextView) view.findViewById(R.id.tvTel);
//            view.setTag(viewHolder);
        }
        TextView t1 = (TextView) view.findViewById(R.id.tvname);
        TextView t2 = (TextView) view.findViewById(R.id.tvTel);

        ResInfo one = data.get(i);
        t1.setText(one.getName());
        t2.setText(one.getTel());

        return view;
    }
//    class ViewHolder{
//        TextView t1;
//        TextView t2;
//    }

    Comparator<ResInfo> nameAsc = new Comparator<ResInfo>() {
        @Override
        public int compare(ResInfo resInfo, ResInfo t1) {
            return resInfo.getName().compareTo(t1.getName());
        }
    };
    final static int NAME_ASC = 0;
    final static int NAME_DESC = 1;

    public void setNameAscSort(int sortType) {
        Collections.sort(data, nameAsc);
        this.notifyDataSetChanged();
    }
}
