package com.example.park.myapplication;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.zip.Inflater;

/**
 * Created by Park on 2017-03-30.
 */

public class Fragment2 extends Fragment  {
    Button b1, b2, b3;
    EditText e1, e2, e3;
    TextView t1, t2, t3, t4, t5, t6;
    RadioButton r1, r2, r3;
    View fragview;
    String table="5";

    Data D ;
    Data d1 ;
    Data d2 ;
    Data d3 ;
    Data d4 ;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragview = inflater.inflate(R.layout.fragment2, container, false);
        init(fragview);
        if (getArguments() != null) {
            table = getArguments().getString("Table");
            Log.d("Table11", table);
        } else {
            Log.d("Table11", "null");
        }
        SetTable(selectTable());
        b1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
//                Data[] Table = new Data[4];
//                Table[0] = new Data("사과테이블",0,0,"",0);
                view = View.inflate(getContext(), R.layout.dialog, null);
                init_dlg(view);
                AlertDialog.Builder dlg = new AlertDialog.Builder(getContext());
                dlg.setTitle("주문하기")
                        .setView(view)
                        .setPositiveButton("닫기", null)
                        .setNegativeButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String S = e1.getText().toString();
                                String P = e2.getText().toString();
                                if (S.length() == 0 || P.length() == 0) {
                                    Toast.makeText(getContext(), "값을 입력해주세여", Toast.LENGTH_LONG).show();
                                } else {
                                    int cost = Integer.parseInt(S) * 10000 + Integer.parseInt(P) * 12000;
//                                    Data dd = selectTable(table);
                                    SaveData(selectTable(),S,P,cost);
                                    Snackbar.make(fragview, "주문되었습니다.", Snackbar.LENGTH_SHORT).show();
                                }
                            }
                        }).show();
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                d1 = new Data("",0,0,"",0);
                t1.setText(d1.time + "");
                t2.setText(d1.pizza+"");
                t3.setText(d1.spaghetti+"");
            }
        });
        return fragview;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    void init(View v) {

        D = new Data("테이블",0,0,"",0);
        d1 = new Data("사과테이블",0,0,"",0);
        d2 = new Data("포도테이블",0,0,"",0);
        d3 = new Data("키위테이블",0,0,"",0);
        d4 = new Data("자몽테이블",0,0,"",0);

        b1 = (Button) v.findViewById(R.id.order);
        b2 = (Button) v.findViewById(R.id.modify);
        b3 = (Button) v.findViewById(R.id.reset);
//        Data table1Data = new Data("포도table");
//        table1Data.tableName = "사과테이블";
        t6 = (TextView) v.findViewById(R.id.table);
        t1 = (TextView) v.findViewById(R.id.time);
        t2 = (TextView) v.findViewById(R.id.pizza);
        t3 = (TextView) v.findViewById(R.id.spaghetti);
        t4 = (TextView) v.findViewById(R.id.cost);
        t5 = (TextView) v.findViewById(R.id.membership);
    }

    void init_dlg(View view) {
        e1 = (EditText) view.findViewById(R.id.spaghettiedit);
        e2 = (EditText) view.findViewById(R.id.pizzaedit);
        r1 = (RadioButton) view.findViewById(R.id.r1);
        r2 = (RadioButton) view.findViewById(R.id.r2);
        r3 = (RadioButton) view.findViewById(R.id.r3);
    }


    public static Fragment2 newInstance(int param1) {
        Fragment2 fragment = new Fragment2();
        Bundle args = new Bundle();
        args.putString("Table", param1 + "");
        fragment.setArguments(args);
        return fragment;
    }

    Data selectTable() {
        Log.d("Table1111",table);
        if(Integer.parseInt(table) == 0) {
            return d1;
        }
        else if(Integer.parseInt(table) == 1) {
            return d2;
        }
        else if(Integer.parseInt(table) == 2){
            return d3;
        }
        else if(Integer.parseInt(table) == 3) {
            return d4;
        }
        else{
            return D;
        }
    }

    void SetText(Data d) {
        t6.setText(d.tableName);
        t1.setText(d.time);
        t2.setText(d.spaghetti + "개");
        t3.setText(d.pizza + "개");
        t4.setText(d.membership);
        t5.setText(d.result + "원");
    }
    void SetTable(Data d) {
        t6.setText(d.tableName);
    }

    void SaveData(Data d, String S, String P, int cost) {
        d.spaghetti = Integer.parseInt(S);
        d.pizza = Integer.parseInt(P);
        if (r1.isChecked()) {
            d.membership = "없음";
            r1.setChecked(true);
        } else if (r2.isChecked()) {
            d.membership = "기본멤버쉽";
            r2.setChecked(true);
            cost -= cost * 0.1;
        } else if (r3.isChecked()) {
            d.membership = "VIP멤버쉽";
            r3.setChecked(true);
            cost -= cost * 0.3;
        }
        d.result = cost;
        SetText(d);
        ButtonEnable(d);
    }


    void ButtonEnable(Data d) {
        if(d.result == 0) {
            b1.setEnabled(false);
            b2.setEnabled(false);
        }
        else{
            b1.setEnabled(true);
            b2.setEnabled(true);
        }
    }
}
