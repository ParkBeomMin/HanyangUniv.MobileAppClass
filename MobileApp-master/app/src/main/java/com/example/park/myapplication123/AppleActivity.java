package com.example.park.myapplication123;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AppleActivity extends AppCompatActivity {
    EditText e1, e2;
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apple);
        setTitle("사과가격 계산하기");

        e1 = (EditText) findViewById(R.id.applePriceText);
        e2 = (EditText) findViewById(R.id.appleNumText);
        b1 = (Button) findViewById(R.id.resultbutton);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String price = e1.getText().toString();
                String num = e2.getText().toString();
                try {
                    if (price.length() == 0 || num.length() == 0) {
                        if (price.length() == 0) {
                            e1.requestFocus();
                        } else {
                            e2.requestFocus();
                        }
                        Toast.makeText(getApplicationContext(), "값을 입력해주세요.", Toast.LENGTH_SHORT).show();
                    } else {
                        int result = (Integer.parseInt(num)) * (Integer.parseInt(price));

                        Toast.makeText(getApplicationContext(), "사과의 가격은 " + result + "원 입니다.", Toast.LENGTH_SHORT).show();
                    }
                }
                catch(NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "숫자만 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
