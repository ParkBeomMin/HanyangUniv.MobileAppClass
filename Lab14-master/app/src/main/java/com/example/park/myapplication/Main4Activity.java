package com.example.park.myapplication;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

public class Main4Activity extends AppCompatActivity {
    TextView t1;
    EditText e1, e2;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        t1 = (TextView) findViewById(R.id.loginTv);
        e1 = (EditText) findViewById(R.id.idEt);
        e2 = (EditText) findViewById(R.id.pwEt);
        b1 = (Button) findViewById(R.id.loginBtn);
    }

    boolean isStart = true;

    public void MyOnClick(View view) {
        thread.start();
//        flag = true;
//        thread.interrupt();
    }

    Boolean flag = false;
    Handler handler = new Handler();
    Thread thread = new Thread() {
        @Override
        public void run() {
            super.run();
            URL url = null;
//            while (!flag) {
            try {
                url = new URL("http://jerry1004.dothome.co.kr/info/login.php");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            HttpURLConnection httpURLConnection =
                    null;
            try {
                httpURLConnection = (HttpURLConnection) url.openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                httpURLConnection.setRequestMethod("POST");
            } catch (ProtocolException e) {
                e.printStackTrace();
            }
            httpURLConnection.setDoOutput(true);
            String postData = "userid=" + URLEncoder.encode(e1.getText().toString())
                    + "&password=" + URLEncoder.encode(e2.getText().toString());
            OutputStream outputStream = null;
            try {
                outputStream = httpURLConnection.getOutputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                outputStream.write(postData.getBytes("UTF-8"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                outputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            InputStream inputStream = null;
            try {
                if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK)
                    inputStream = httpURLConnection.getInputStream();
                else
                    inputStream = httpURLConnection.getErrorStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            final String result = loginResult(inputStream);
            Log.d("BEOM1", result);
//            while(!flag)
            handler.post(new Runnable() {
                @Override
                public void run() {
                    if (result.equals("FAIL\n")) {
                        t1.setText("로그인이 실패했습니다 .");
//                        thread.interrupted();
                        Intent intent = new Intent(Main4Activity.this, Main4Activity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        t1.setText(result + "님 로그인 성공");
                        e1.setEnabled(false);
                        e2.setEnabled(false);
                        b1.setEnabled(false);
//                            flag = true;
//                            thread.interrupt();
                    }
                }
            });
        }
//        }

        String loginResult(InputStream is) {
            String data = "";
            Scanner s = new Scanner(is);
            while (s.hasNext()) data += s.nextLine() + "\n";
            s.close();
            return data;
        }
    };
}
