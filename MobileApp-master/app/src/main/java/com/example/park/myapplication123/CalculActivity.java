package com.example.park.myapplication123;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CalculActivity extends AppCompatActivity {
    EditText num1, num2;
    Button plus, minus, divide, multi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcul);
        setTitle("계산기");

        num1 = (EditText) findViewById(R.id.num1editText);
        num2 = (EditText) findViewById(R.id.num2editText);

        plus = (Button) findViewById(R.id.plusbutton);
        minus = (Button) findViewById(R.id.minusbutton);
        divide = (Button) findViewById(R.id.dividebutton);
        multi = (Button) findViewById(R.id.multibutton);


        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Num1 = num1.getText().toString();
                String Num2 = num2.getText().toString();
                try{
                if (Num1.length() == 0 || Num2.length() == 0) {
                    if (Num1.length() == 0) {
                        num1.requestFocus();
                    } else {
                        num2.requestFocus();
                    }
                    Toast.makeText(getApplicationContext(), "값을 입력해주세요.", Toast.LENGTH_SHORT).show();
                } else {
                    int result;
                    result = Integer.parseInt(Num1) + Integer.parseInt(Num2);

                    Toast.makeText(getApplicationContext(), "더하기 계산 결과는 " + result + "입니다.", Toast.LENGTH_SHORT).show();
                }
            }
            catch(NumberFormatException e) {
                Toast.makeText(getApplicationContext(), "숫자만 입력해주세요.", Toast.LENGTH_SHORT).show();
            }
        }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Num1 = num1.getText().toString();
                String Num2 = num2.getText().toString();

                try{
                if (Num1.length() == 0 || Num2.length() == 0) {
                    if (Num1.length() == 0) {
                        num1.requestFocus();
                    } else {
                        num2.requestFocus();
                    }
                    Toast.makeText(getApplicationContext(), "값을 입력해주세요.", Toast.LENGTH_SHORT).show();
                } else {
                    int result;
                    if (Integer.parseInt(Num1) > Integer.parseInt(Num2))
                        result = Integer.parseInt(Num1) - Integer.parseInt(Num2);
                    else
                        result = Integer.parseInt(Num2) - Integer.parseInt(Num1);
                    Toast.makeText(getApplicationContext(), "빼기 계산 결과는 " + result + "입니다.", Toast.LENGTH_SHORT).show();
                }
            }
            catch(NumberFormatException e) {
                Toast.makeText(getApplicationContext(), "숫자만 입력해주세요.", Toast.LENGTH_SHORT).show();
            }
        }
        });
        divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Num1 = num1.getText().toString();
                String Num2 = num2.getText().toString();
                try{
                if (Num1.length() == 0 || Num2.length() == 0) {
                    if (Num1.length() == 0) {
                        num1.requestFocus();
                    } else {
                        num2.requestFocus();
                    }
                    Toast.makeText(getApplicationContext(), "값을 입력해주세요.", Toast.LENGTH_SHORT).show();
                } else {
                    double result;
                    result = Double.parseDouble(Num1) / Double.parseDouble(Num2);

                    Toast.makeText(getApplicationContext(), "나누기 계산 결과는 " + (int) result + "입니다.", Toast.LENGTH_SHORT).show();
                }
            }
            catch(NumberFormatException e) {
                Toast.makeText(getApplicationContext(), "숫자만 입력해주세요.", Toast.LENGTH_SHORT).show();
            }
        }
        });
        multi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Num1 = num1.getText().toString();
                String Num2 = num2.getText().toString();

                try {
                    if (Num1.length() == 0 || Num2.length() == 0) {
                        if (Num1.length() == 0) {
                            num1.requestFocus();
                        } else {
                            num2.requestFocus();
                        }
                        Toast.makeText(getApplicationContext(), "값을 입력해주세요.", Toast.LENGTH_SHORT).show();
                    } else {
                        int result;
                        result = Integer.parseInt(Num1) * Integer.parseInt(Num2);

                        Toast.makeText(getApplicationContext(), "곱하기 계산 결과는 " + result + "입니다.", Toast.LENGTH_SHORT).show();
                    }
                }
                catch(NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "숫자만 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
