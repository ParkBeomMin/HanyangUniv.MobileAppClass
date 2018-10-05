package com.example.park.myapplication;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Main2Activity extends AppCompatActivity {
    EditText e1, e2;

    String urlstr = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        e1 = (EditText) findViewById(R.id.urlEt);
        e2 = (EditText) findViewById(R.id.showurlEt);
        e1.setText("http://www.google.co.kr");
        urlstr = e1.getText().toString();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 1, 0, "다음으로");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(this, Main3Activity.class);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }

    public void MyOnClick(View view) {
        thread.start();
    }

    Handler handler = new Handler();
    Thread thread = new Thread() {
        @Override
        public void run() {
            URL url =
                    null;
            try {
                url = new URL(urlstr);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }


            HttpURLConnection urlConnection =
                    null;
            try {
                urlConnection = (HttpURLConnection) url.openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    final String data = readData(urlConnection.getInputStream());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            e2.setText(data);
                        }
                    });
                    urlConnection.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String readData(InputStream is) {
            String data = "";
            Scanner s = new Scanner(is);
            while (s.hasNext()) data += s.nextLine() + "\n";
            s.close();
            return data;
        }
    };
}
