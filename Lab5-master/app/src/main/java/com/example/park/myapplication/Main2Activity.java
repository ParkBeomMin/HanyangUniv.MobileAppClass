package com.example.park.myapplication;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

public class Main2Activity extends AppCompatActivity {
    EditText e1, e2, e3, e4, e5, e6;
    String[] array = new String[3];
    Data d1;
    String date;
    RadioButton r1, r2, r3;
    int check;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        setTitle("나의 맛집");
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    void init() {
        Intent intent = getIntent();
        d1 = intent.getParcelableExtra("data");

        e1 = (EditText) findViewById(R.id.etname);
        e2 = (EditText) findViewById(R.id.ettel);
        e3 = (EditText) findViewById(R.id.etmenu1);
        e4 = (EditText) findViewById(R.id.etmenu2);
        e5 = (EditText) findViewById(R.id.etmenu3);
        e6 = (EditText) findViewById(R.id.etaddr);

        r1 = (RadioButton) findViewById(R.id.radio1);
        r2 = (RadioButton) findViewById(R.id.radio2);
        r3 = (RadioButton) findViewById(R.id.radio3);

        date = Currtime();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    String Currtime() {
        long x = System.currentTimeMillis();
        Date date = new Date(x);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String ftime = sdf.format(date);
        return ftime;
    }

    int radioCheck() {
        if (r1.isChecked()) {
            check = 0;
        } else if (r2.isChecked()) {
            check = 1;
        } else {
            check = 2;
        }
        return check;
    }

    String[] insertArr() {
        array[0]=e3.getText().toString();
        array[1]=e4.getText().toString();
        array[2]=e5.getText().toString();
        return array;
    }

    public void onClick(View v) {
        if (v.getId() == R.id.btnAdd) {
            String name = e1.getText().toString();
            String call = e2.getText().toString();
            String m1 = e3.getText().toString();
            String m2 = e4.getText().toString();
            String m3 = e5.getText().toString();
            String home = e6.getText().toString();
            if(name.length() == 0 || call.length() == 0 || m1.length() == 0 || m2.length() == 0 || m3.length() == 0 || home.length() == 0) {
                Toast.makeText(Main2Activity.this, "모든 값을 입력해주세요!! 없다면 '없음'을 적어주세요.", Toast.LENGTH_LONG).show();
            }
            else {
                d1 = new Data(e1.getText().toString(), e2.getText().toString(), insertArr(), e6.getText().toString(), date, radioCheck());
                Log.d("ARR1", insertArr()[0]);
                Intent intent = new Intent();
                intent.putExtra("data", d1);
                setResult(RESULT_OK, intent);
                finish();
            }
        } else if (v.getId() == R.id.btnCancel) {

        }
    }
}
