package com.example.park.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    ListView l1;
    ArrayList<ResInfo> data = new ArrayList<ResInfo>();
    ResAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        init();
    }

    void init() {
        l1 = (ListView) findViewById(R.id.l1);

        data.add(new ResInfo("MYPizza", "01033334444", 1));
        data.add(new ResInfo("BBQ", "01011112222", 0));
        data.add(new ResInfo("SubWay", "01055556666", 2));

        adapter = new ResAdapter(this, data);
        l1.setAdapter(adapter);
    }

    public void MyOnClick(View view) {
        if(view.getId() == R.id.b1) {
            adapter.setNameAscSort(ResAdapter.NAME_ASC);
        }
    }
}
