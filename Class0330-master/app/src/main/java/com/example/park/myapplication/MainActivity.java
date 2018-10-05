package com.example.park.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
Button b1, b2, b3, b4, b5,b6,b7,b8;
    EditText e1, e2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    void init() {
        b1 = (Button) findViewById(R.id.button);
        b2 = (Button) findViewById(R.id.button2);
        b3 = (Button) findViewById(R.id.button3);
        b4 = (Button) findViewById(R.id.button4);
        b5 = (Button) findViewById(R.id.button5);
        b6 = (Button) findViewById(R.id.button6);
        b7 = (Button) findViewById(R.id.button7);
        b8 = (Button) findViewById(R.id.button8);


        e1 = (EditText) findViewById(R.id.editText);
        e2 = (EditText) findViewById(R.id.editText2);
    }

   public void ToastClick(View v) {

        if(v == b1) {
            Toast.makeText(getApplicationContext(), "일반 메시지 창",Toast.LENGTH_SHORT).show();
        }
        else if (v == b2) {
            Toast toast = Toast.makeText(this, "중간", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
        else if (v == b3) {
            View view = getLayoutInflater().inflate(R.layout.mytoast, null);
                    //View.inflate(getApplicationContext(), R.layout.mytoast, null);

            TextView t1 = (TextView)view.findViewById(R.id.toast);
            t1.setText("레이아웃으로 만든거");
            Toast toast = new Toast(this);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER,0,100);
            toast.setView(view);
            toast.show();
        }
       else if( v == b4) {
            Snackbar.make(v, "스낵바로 보여주는 메시지", Snackbar.LENGTH_SHORT)
                    .setAction("OK", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        }
                    }).show();
        }
        else if( v == b5) {
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle("제목")
                    .setMessage("내용")
                    .setIcon(R.mipmap.ic_launcher)
                    .setPositiveButton("닫기",null)
                    .setNegativeButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(getApplicationContext(), ",확인을눌렀습니다.",Toast.LENGTH_LONG).show();
                        }
                    })
                    .show();

//            dlg.setTitle("제목");
//            dlg.setMessage("내용");
        }
        else if( v == b6) {
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            String data[] = {"치킨","피자"};
            dlg.setTitle("먹고싶은 메뉴는 ??")
                    .setSingleChoiceItems(data,1, null)
                    .setIcon(R.mipmap.ic_launcher)
                    .setPositiveButton("닫기",null)
                    .setNegativeButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(getApplicationContext(), ",확인을눌렀습니다.",Toast.LENGTH_LONG).show();
                        }
                    })
                    .show();
        }
        else if( v == b7) {
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            final String data[] = {"치킨","피자","짜장","탕슉"};
            final boolean check[] = {true,false,true,false};
            dlg.setTitle("먹고싶은 메뉴는 ??")
                    .setMultiChoiceItems(data, check, new DialogInterface.OnMultiChoiceClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                            check[i] = b;
                        }
                    })
                    .setIcon(R.mipmap.ic_launcher)
                    .setPositiveButton("닫기",null)
                    .setNegativeButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            String result = "";
                            for(int j = 0; j < check.length; j++) {
                                if(check[j] == true) {
                                    result += " ," + data[j];
                                }
                            }
                            Toast.makeText(getApplicationContext(), result.substring(1),Toast.LENGTH_LONG).show();
                        }
                    })
                    .show();
        }
        else if( v == b8) {

            View view = View.inflate(this, R.layout.dia, null);
            final EditText e3 = (EditText)view.findViewById(R.id.editText3) ;
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);

            dlg.setTitle("먹고싶은 메뉴는 ??")
                   .setView(view)
                    .setIcon(R.mipmap.ic_launcher)
                    .setPositiveButton("닫기",null)
                    .setNegativeButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            Toast.makeText(getApplicationContext(), e3.getText().toString(),Toast.LENGTH_LONG).show();
                        }
                    })
                    .show();
        }
    }

    int input(int i) {
        String X = e1.getText().toString();
        String Y = e2.getText().toString();
        int j = 0;
        if(X.length() == 0 || Y.length() == 0) {

        }
        else {
            if(i == 0) {
                j =  Integer.parseInt(X);
            }
            else {
                j = Integer.parseInt(Y);
            }
        }
        return j;
    }
}
