package com.example.park.myapplication123;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TemperActivity extends AppCompatActivity {
    EditText cel , fah;
    Button FahCalcul, CelCalcul;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temper);
        setTitle("온도변환기");

        cel = (EditText) findViewById(R.id.CeleditText);
        fah = (EditText) findViewById(R.id.FaheditText);

        FahCalcul = (Button) findViewById(R.id.FahCalculbutton);
        FahCalcul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Cel = cel.getText().toString();
                try {
                    if (Cel.length() == 0) {
                        Toast.makeText(getApplicationContext(), "값을 입력해주세요.", Toast.LENGTH_SHORT).show();
                        cel.requestFocus();
                    } else {
                        Double result = Integer.parseInt(Cel) * 1.8 + 32;
                        Toast.makeText(getApplicationContext(), "화씨 온도는 " + result + "입니다.", Toast.LENGTH_SHORT).show();
                    }

                }
                catch(NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "숫자만 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        CelCalcul = (Button) findViewById(R.id.CelCalculbutton);
        CelCalcul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Fah = fah.getText().toString();
                try {
                    if (Fah.length() == 0) {
                        Toast.makeText(getApplicationContext(), "값을 입력해주세요.", Toast.LENGTH_SHORT).show();
                        fah.requestFocus();
                    } else {
                        Double result = (Integer.parseInt(Fah) - 32) / 1.8;
                        Toast.makeText(getApplicationContext(), "섭씨 온도는 " + result + "입니다.", Toast.LENGTH_SHORT).show();
                    }

                }
                catch(NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "숫자만 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
