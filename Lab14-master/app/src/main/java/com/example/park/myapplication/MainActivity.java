package com.example.park.myapplication;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    String SERVER_IP = "172.17.66.52";
    int SERVER_PORT = 200;
    String msg = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    Handler myHandler = new Handler();
    Thread myThread = new Thread() {
        @Override
        public void run() {
            super.run();
            try {
                Socket aSocket = new Socket(SERVER_IP, SERVER_PORT);
                System.out.println("Client] 서버 접속");

                Scanner s = new Scanner(System.in);
                System.out.println("서버에 보낼 데이터를 입력하세요 ");
                String data = s.next();

                //서버에 보낼 데이터
                ObjectOutputStream outstream = new ObjectOutputStream(aSocket.getOutputStream());
                outstream.writeObject(msg);
                outstream.flush();


                ObjectInputStream instream = new ObjectInputStream(aSocket.getInputStream());
                final Object obj = instream.readObject();
                myHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), (String) obj, Toast.LENGTH_LONG).show();
                    }
                });

                aSocket.close();


            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
    };


}
