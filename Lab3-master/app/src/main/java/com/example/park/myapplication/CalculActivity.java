package com.example.park.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

public class CalculActivity extends AppCompatActivity {
    TabHost tabHost ;
    TextView t1, t2, t3;
    EditText e1, e2, e3, e4, e5, e6;
    Button b1, b2, b3, b4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcul);
        setTitle("각종 계산기");
        init();
        tab();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu2, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.prev) {
            Intent intent = new Intent(this, MenuActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    void init(){
        t1 = (TextView) findViewById(R.id.resultBMI);
        t2 = (TextView) findViewById(R.id.result);

        e1 = (EditText) findViewById(R.id.height);
        e2 = (EditText) findViewById(R.id.weight);
        e3 = (EditText) findViewById(R.id.input);

        b1 = (Button) findViewById(R.id.btnBIM);
        b2 = (Button) findViewById(R.id.btn1);
        b3 = (Button) findViewById(R.id.btn2);

        tabHost = (TabHost)findViewById(R.id.tabhost);
    }
    void tab() {
        tabHost.setup();
        tabHost.addTab(tabHost.newTabSpec("A").setContent(R.id.tab1).setIndicator("BMI계산기"));
        tabHost.addTab(tabHost.newTabSpec("B").setContent(R.id.tab2).setIndicator("면적계산기"));
        tabHost.addTab(tabHost.newTabSpec("C").setIndicator("질량계산기").setContent(new TabHost.TabContentFactory() {
            @Override
            public View createTabContent(String s) {
                return GramView();
            }
        }));
    }
    View GramView() {
        View view = View.inflate(CalculActivity.this, R.layout.activity_gram, null);
        Log.d("GRAM","1");
        Gram_init(view);
//        b4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Gram();
//            }
//        });
            Log.d("GRAM","2");
        return view;
    }
    void Gram_init(View view) {
        b4 = (Button) view.findViewById(R.id.btnGram);
        e4 = (EditText) view.findViewById(R.id.Normal);
        e5 = (EditText) view.findViewById(R.id.molWeight);
        e6 = (EditText) view.findViewById(R.id.eqNumber);
        t3 = (TextView) view.findViewById(R.id.GramResult);
    }

    void Gram() {
        String N = e4.getText().toString();
        String M = e5.getText().toString();
        String E = e6.getText().toString();
        try {
            if (N.length() == 0 || M.length() == 0 || E.length() == 0) {
                if (N.length() == 0) {
                    e4.requestFocus();
                } else if (M.length() == 0) {
                    e5.requestFocus();
                } else if (E.length() == 0) {
                    e6.requestFocus();
                }
                t3.setText("값을 입력해주세요.");
                t3.setTextColor(Color.RED);
            } else {
                double gram;
                gram = (Double.parseDouble(N) * Double.parseDouble(M)) / Double.parseDouble(E);
                t3.setTextColor(Color.BLUE);
                t3.setText(gram + "g");
            }
        }
        catch (NumberFormatException e) {
            Toast.makeText(getApplicationContext(), "숫자만 입력해주세요.", Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickCalCul(View view) {
        if(view == b1) {
            BMI();
        }
        else if(view == b2) {
            area(0);
            //PtoM();
        }
        else if(view == b3) {
           area(1);
           // MtoP();
        }
        else if(view == b4) {
            Gram();
        }
    }
    void BMI() {
        String H = e1.getText().toString();
        String W = e2.getText().toString();
        if (H.length() == 0 || W.length() == 0) {
            if (H.length() == 0) {
                e1.requestFocus();
            } else if (W.length() == 0) {
                e2.requestFocus();
            }
        } else {
            double bmi;
            bmi = Double.parseDouble(W) / Math.pow(Double.parseDouble(H)/100,2);
            Log.d("BMI",bmi+"");
            if (bmi < 18.5) {
                t1.setTextColor(Color.BLUE);
                t1.setText("체중미달");
            } else if (bmi >= 18.5 && bmi <= 22.9) {
                t1.setTextColor(Color.GREEN);
                t1.setText("정상");
            } else if (bmi >= 23.0 && bmi <= 24.9) {
                t1.setTextColor(Color.YELLOW);
                t1.setText("과체중");
            } else if (bmi >= 25.0) {
                t1.setTextColor(Color.RED);
                t1.setText("비만입니다!!!");
            }
        }
    }

    void area(int i) {
        String P = e3.getText().toString();
        String M = e3.getText().toString();
        if(P.length() == 0 || M.length() == 0) {
            Toast.makeText(getApplication(), "입력해주세요.", Toast.LENGTH_SHORT).show();
        }
        else {
            double a;
            if(i == 0) {
            a = Double.parseDouble(P) * 3.305785;
            t2.setText(a + "m^2");
            }
            else {
            a = Double.parseDouble(M) / 3.305785;
            t2.setText(a + "평");
            }
        }
    }

//    void PtoM() {
//        String P = e3.getText().toString();
//        if(P.length() == 0) {
//
//        }
//        else {
//            double m;
//            m = Double.parseDouble(P) * 3.305785;
//            t2.setText(m + "m^2");
//        }
//    }
//
//    void MtoP() {
//        String M = e3.getText().toString();
//        if(M.length() == 0) {
//
//        }
//        else {
//            double p;
//            p = Double.parseDouble(M) / 3.305785;
//            t2.setText(p + "평");
//        }
//    }
}
