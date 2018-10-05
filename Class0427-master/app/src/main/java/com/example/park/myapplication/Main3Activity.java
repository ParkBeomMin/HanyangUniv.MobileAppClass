package com.example.park.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Filterable;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class Main3Activity extends AppCompatActivity {
    ArrayList<Fruit> fruit = new ArrayList<Fruit>();

    GridView gridView;
    GridAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        gridView = (GridView) findViewById(R.id.grid);
        fruit.add(new Fruit("아보카도", FRUITDATA.imglist[0]));
        fruit.add(new Fruit("수박", FRUITDATA.imglist[1]));
        fruit.add(new Fruit("오렌지", FRUITDATA.imglist[2]));
        fruit.add(new Fruit("키위", FRUITDATA.imglist[3]));
        adapter = new GridAdapter(this, fruit);
        gridView.setAdapter(adapter);

        AddFruit addFruit = (AddFruit) findViewById(R.id.add);
        addFruit.setOnAddListener(new AddFruit.OnAddListener() {
            @Override
            public void onAdd(String name, int imageno) {
                Toast.makeText(getApplicationContext(), name + "" + imageno, Toast.LENGTH_LONG).show();
                adapter.addFruit(new Fruit(name, FRUITDATA.imglist[imageno]));
                adapter.refresh();
            }


        });
    }
}
