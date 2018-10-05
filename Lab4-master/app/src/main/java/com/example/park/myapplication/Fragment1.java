package com.example.park.myapplication;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by Park on 2017-03-30.
 */

public class Fragment1 extends Fragment {
    final String[] data = {"사과 Table(비어있음)","포도 Table(비어있음)","키위 Table(비어있음)", "자몽 Table(비어있음)"};



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null) {
            Log.d("Table1", getArguments().getString("Table"));
        }
        else {

            Log.d("Table1", "null1");
        }

    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1,null);
        ArrayAdapter adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1 , data) ;
        ListView listview = (ListView) view.findViewById(R.id.listview) ;
        listview.setAdapter(adapter) ;
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Toast.makeText(getContext(), ""+adapterView.getItemAtPosition(i),Toast.LENGTH_LONG).show();
                Log.d("Table",i+"");
                sl.selected(i);

            }
        });
        return view;
    }

    public interface selectListner {
        void selected(int i);
    }
    private selectListner sl;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            sl = (selectListner) context;
        }
        catch (ClassCastException e ) {
            throw new ClassCastException(context.toString() + " must implement selectListner");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        sl = null;
    }
}
