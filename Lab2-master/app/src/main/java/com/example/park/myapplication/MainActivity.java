package com.example.park.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button b1, b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    public void onClick(View v) {//리퀘스트 받아줘
        if(v.getId() == R.id.button1) {//test
            Log.d("confirm","good");
            Intent intent = new Intent(MainActivity.this, GradeActivity.class);
            startActivity(intent);
        }

        if(v.getId() == R.id.button2) {
            Intent intent = new Intent(MainActivity.this, ResActivity.class);
            startActivity(intent);
        }
    }
    void init() {
        b1 = (Button) findViewById(R.id.button1);
        b2 = (Button) findViewById(R.id.button2);
    }
}
