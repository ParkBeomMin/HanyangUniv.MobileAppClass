package com.example.park.myapplication123;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AgeActivity extends AppCompatActivity {
    EditText year, age;
    Button agecalcul, birth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_age);
        setTitle("나이 계산하기");

        year = (EditText) findViewById(R.id.yeareditText);
        age = (EditText) findViewById(R.id.ageeditText);

        agecalcul = (Button) findViewById(R.id.agecalculbutton);
        agecalcul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Year = year.getText().toString();
                try {
                    if (Year.length() == 0) {
                        Toast.makeText(getApplicationContext(), "값을 입력해주세요.", Toast.LENGTH_SHORT).show();
                        year.requestFocus();
                    } else if (Integer.parseInt(Year) > 2017) {
                        Toast.makeText(getApplicationContext(), "태어난 년도가 현재 년도보다 늦습니다.", Toast.LENGTH_SHORT).show();
                    } else {
                        int result = 2017 - Integer.parseInt(Year) + 1;
                        Toast.makeText(getApplicationContext(), "당신의 나이는 " + result + "세입니다.", Toast.LENGTH_SHORT).show();
                    }
                }
                catch(NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "숫자만 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        birth = (Button) findViewById(R.id.birthbutton);
        birth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Age = age.getText().toString();
                try {
                    if (Age.length() == 0) {
                        Toast.makeText(getApplicationContext(), "값을 입력해주세요.", Toast.LENGTH_SHORT).show();
                        age.requestFocus();
                    } else if (Integer.parseInt(Age) < 1) {
                        Toast.makeText(getApplicationContext(), "1살보다 어릴수는 없습니다.", Toast.LENGTH_SHORT).show();
                    } else {
                        int result = 2017 - Integer.parseInt(Age) + 1;
                        Toast.makeText(getApplicationContext(), "태어난 년도는" + result + "년입니다.", Toast.LENGTH_SHORT).show();
                    }
                }
                catch(NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "숫자만 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
