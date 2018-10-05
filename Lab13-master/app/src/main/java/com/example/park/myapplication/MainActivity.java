package com.example.park.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText e1;
    ImageButton i1;
    TextView t1;
    ArrayList<Integer> arrayList = new ArrayList<Integer>();
    ArrayList<String> FoodName = new ArrayList<String>();
    myTask task;
    int ImageChange;
    int CHOOSE = 0;
    int ChooseImage;
    int time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    void init() {
        t1 = (TextView) findViewById(R.id.timer);
        e1 = (EditText) findViewById(R.id.secondEt);
        i1 = (ImageButton) findViewById(R.id.image);
        arrayList.add(R.drawable.chicken);
        FoodName.add("치킨");
        arrayList.add(R.drawable.pizza);
        FoodName.add("피자");
        arrayList.add(R.drawable.salad);
        FoodName.add("샐러드");
        arrayList.add(R.drawable.hamburger);
        FoodName.add("햄버거");
    }


    public void onClick(View v) {
        if (v.getId() == R.id.initBtn) {
            if (CHOOSE == 0) {
                startActivity(new Intent(MainActivity.this, MainActivity.class));
                finish();
            }
            else {
                Toast.makeText(getApplicationContext(), "메뉴를 골라주세요!", Toast.LENGTH_LONG).show();
            }
        } else {
            if (CHOOSE == 0) {
                ImageChange = Integer.parseInt(e1.getText().toString());
                Log.d("BEOM3", "ImageChange : " + ImageChange);
                task = new myTask();
                task.execute();
                CHOOSE++;
            } else {
                task.cancel(true);
                t1.setTextColor(Color.RED);
                t1.setText(FoodName.get(ChooseImage) + " 선택" + " ("+ time + "초 경과)");
                CHOOSE = 0;
            }
        }
    }

    class myTask extends AsyncTask<Integer, Integer, Void> {
        @Override
        protected void onPreExecute() {
            t1.setVisibility(View.VISIBLE);
            t1.setText("경과시간 : 0초");
            i1.setImageResource(arrayList.get(0));
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Integer... integers) {

//            Log.d("BEMO1", "op : " + op);
            for (int i = 1, j = 0; i < 9999; i++) {
                try {
                    //+"\n"+"integers : " + integers[0]);
                    if (isCancelled() == true) {
                        return null;
                    }
                    Log.d("BEMO1", "op : " + ImageChange);
                    Thread.sleep(1000);
                    if (i % ImageChange == 0) {
                        publishProgress(i, ++j);
                    } else {
                        publishProgress(i);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            t1.setText("경과시간 : " + values[0] + "초");
            time = values[0];
            if (values.length != 1) {
                Log.d("BEOM", "values[0] : " + values[0] + "\n" + "values[1] : " + values[1]);
                i1.setImageResource(arrayList.get(values[1] % arrayList.size()));
                ChooseImage = values[1] % arrayList.size();
            }
            super.onProgressUpdate(values);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }
    @Override
    public void onBackPressed() {
        System.exit(0);
    }
}
