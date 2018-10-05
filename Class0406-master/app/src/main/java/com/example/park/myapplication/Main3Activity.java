package com.example.park.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class Main3Activity extends AppCompatActivity {
    ListView l1;
    EditText e1;
    ArrayList<String> data = new ArrayList<String>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        setListView();
    }

    public void setListView() {
        l1 = (ListView) findViewById(R.id.list);
        e1 = (EditText) findViewById(R.id.AddeditText);
        //데이터 만들기
        data.add("사과");
        data.add("포도");
        data.add("복숭아");

        //어댑터만들기
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);

        //어댑터 꽂아주기
        l1.setAdapter(adapter);
        l1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                data.remove(i);
                adapter.notifyDataSetChanged();
                return false;
            }
        });
    }

    public void onClick(View view) {
        if (view.getId() == R.id.AddButton) {
            data.add(e1.getText().toString());
            adapter.notifyDataSetChanged();
        }
    }
}
