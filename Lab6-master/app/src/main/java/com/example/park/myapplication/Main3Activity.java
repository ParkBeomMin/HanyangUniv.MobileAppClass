package com.example.park.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {
TextView t1, t2, t3, t4, t5, t6, t7;
    Data d;
    ImageView i1, i2, i3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        setTitle("나의 맛집");
        init();
        setText();
    }
    void init() {
        t1 = (TextView) findViewById(R.id.txtname);
        t2 = (TextView) findViewById(R.id.etmenu1);
        t3 = (TextView) findViewById(R.id.etmenu2);
        t4 = (TextView) findViewById(R.id.etmenu3);
        t5 = (TextView) findViewById(R.id.tvTel);
        t6 = (TextView) findViewById(R.id.tvURL);
        t7 = (TextView) findViewById(R.id.tvRegdate);

        i1 = (ImageView) findViewById(R.id.imgno);
        i2 = (ImageView) findViewById(R.id.imageView2);
        i3 = (ImageView) findViewById(R.id.imageView3);
    }

    void setText() {
        Intent intent = getIntent();
        d = intent.getParcelableExtra("position");
        Log.d("PPPPP",d.menu[0]);

        t1.setText(d.name);
        t2.setText(d.menu[0]);
        t3.setText(d.menu[1]);
        t4.setText(d.menu[2]);
        t5.setText(d.call);
        t6.setText(d.homepage);
        t7.setText(d.date);
        if(d.category == 0) {
            i1.setImageResource(R.drawable.chicken);
        }
        else if(d.category == 1) {
            i1.setImageResource(R.drawable.pizza);
        }
        else{
            i1.setImageResource(R.drawable.hamburger);
        }
    }

    public void onClick(View v) {
        if(v.getId() == R.id.btnback) {
            Intent intent = new Intent();
            setResult(RESULT_OK,intent);
            finish();
        }
        else if(v.getId() == R.id.imageView2) {
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:/"+d.call));
            startActivity(intent);
        }
        else if(v.getId() == R.id.imageView3) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://" + d.homepage));
                startActivity(intent);
        }
    }
}
