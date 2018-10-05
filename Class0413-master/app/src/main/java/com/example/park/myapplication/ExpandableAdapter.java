package com.example.park.myapplication;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import java.util.ArrayList;

/**
 * Created by Park on 2017-04-13.
 */

public class ExpandableAdapter extends BaseExpandableListAdapter {
    ArrayList<GroupData> gdata;
    @Override
    public int getGroupCount() {
        return gdata.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return gdata.get(i).childlist.size();
    }

    @Override
    public Object getGroup(int i) {
        return gdata.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return  gdata.get(i).childlist.get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {

        return null;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        return null;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}
