package com.example.park.myapplication123;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ResActivity extends AppCompatActivity {
    EditText pizza, spagetti, salad;
    TextView ordernum, orderprice;
    CheckBox ch;
    Button sum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res);
        setTitle("레스토랑 메뉴 주문 ");

        pizza = (EditText) findViewById(R.id.pizzaeditText);
        spagetti = (EditText) findViewById(R.id.spagettieditText);
        salad = (EditText) findViewById(R.id.saladeditText);
        ordernum = (TextView) findViewById(R.id.ordernum1textView);
        orderprice = (TextView) findViewById(R.id.orderprice2textView);

        ch = (CheckBox) findViewById(R.id.checkBox) ;

        sum = (Button) findViewById(R.id.sumbutton);
        sum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Pizza = pizza.getText().toString();
                String Spagetti = spagetti.getText().toString();
                String Salad = salad.getText().toString();
                ordernum.setText(0 + "개");
                orderprice.setText(0 + "원");
                try {
                    if (Pizza.length() == 0 || Spagetti.length() == 0 || Salad.length() == 0) {
                        Toast.makeText(getApplicationContext(), "값을 입력해주세요.", Toast.LENGTH_SHORT).show();
                        if (Pizza.length() == 0) {
                            pizza.requestFocus();
                        } else if (Pizza.length() != 0 && Spagetti.length() == 0) {
                            spagetti.requestFocus();
                        } else {
                            salad.requestFocus();
                        }
                    } else if (ch.isChecked()) {
                        int num = Integer.parseInt(Pizza) + Integer.parseInt(Spagetti) + Integer.parseInt(Salad);
                        int price = (Integer.parseInt(Pizza) * 15000 + Integer.parseInt(Spagetti) * 13000 + Integer.parseInt(Salad) * 9000) - (Integer.parseInt(Pizza) * 15000 + Integer.parseInt(Spagetti) * 13000 + Integer.parseInt(Salad) * 9000) / 10;
                        ordernum.setText(num + "개");
                        orderprice.setText(price + "원");
                    } else {
                        int num = Integer.parseInt(Pizza) + Integer.parseInt(Spagetti) + Integer.parseInt(Salad);
                        int price = (Integer.parseInt(Pizza) * 15000 + Integer.parseInt(Spagetti) * 13000 + Integer.parseInt(Salad) * 9000);
                        ordernum.setText(num + "개");
                        orderprice.setText(price + "원");
                    }

                }
                catch(NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "숫자만 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
