package com.example.park.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {
MyCanvas2 myCanvas2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myCanvas2 = (MyCanvas2) findViewById(R.id.canvas);
    }
    public void onClick(View v) {
       myCanvas2.setOperationType((String)v.getTag());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 1, 0, "Bluring");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == 1) {
            myCanvas2.setOperationType("BLUR");
        }
        return super.onOptionsItemSelected(item);
    }
}
