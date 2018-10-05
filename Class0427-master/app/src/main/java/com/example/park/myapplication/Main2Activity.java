package com.example.park.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Main2Activity extends AppCompatActivity {
Spinner spinner;
    String[] fruit = {"사과","포도","복숭아"};
SpinnerAdapter adapter;    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        spinner = (Spinner)findViewById(R.id.spinner1);
        adapter = new SpinnerAdapter(fruit, this);
        spinner.setAdapter(adapter);
    }
}
