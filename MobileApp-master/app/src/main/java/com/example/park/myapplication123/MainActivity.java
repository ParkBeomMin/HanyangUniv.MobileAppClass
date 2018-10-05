package com.example.park.myapplication123;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button b1, b2, b3, b4, b5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("실습1주차");

        b1 = (Button) findViewById(R.id.applebutton);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AppleActivity.class);
                startActivity(intent);
            }
        });

        b2 = (Button) findViewById(R.id.calculbutton);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CalculActivity.class);
                startActivity(intent);
            }
        });

        b3 = (Button) findViewById(R.id.agebutton);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AgeActivity.class);
                startActivity(intent);
            }
        });

        b4 = (Button) findViewById(R.id.temperbutton);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TemperActivity.class);
                startActivity(intent);
            }
        });

        b5 = (Button) findViewById(R.id.resbutton);
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ResActivity.class);
                startActivity(intent);
            }
        });
    }
}
