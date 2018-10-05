package com.example.park.myapplication;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
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

/**
 * Created by Park on 2017-03-31.
 */

public class Fragment extends android.support.v4.app.Fragment {
    Button b1, b2, b3, b4, b5, b6, b7;
    TextView t1, t2, t3, t4, t5, t6;
    Data d1, d2, d3, d4;
    EditText e1, e2, e3;
    RadioButton r1, r2, r3;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View fragview = inflater.inflate(R.layout.fragment, null);
        init(fragview);
        ButtonClick(b1, d1);
        ButtonClick(b2, d2);
        ButtonClick(b3, d3);
        ButtonClick(b4, d4);

        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                                    if (t1.getText().toString().equals("사과 테이블")) {
                                        SaveData(b1, d1, S, P, cost, 0);
//                                        t2.setText(d1.Currtime());
                                    } else if (t1.getText().toString().equals("포도 테이블")) {
                                        SaveData(b2, d2, S, P, cost, 0);
//                                        t2.setText(d2.Currtime());
                                    } else if (t1.getText().toString().equals("키위 테이블")) {
                                        SaveData(b3, d3, S, P, cost, 0);
//                                        t2.setText(d3.Currtime());
                                    } else if (t1.getText().toString().equals("자몽 테이블")) {
                                        SaveData(b4, d4, S, P, cost, 0);
//                                        t2.setText(d4.Currtime());
                                    }
                                    b5.setEnabled(false);
                                    Snackbar.make(fragview, "주문되었습니다.", Snackbar.LENGTH_SHORT).show();
                                }
                            }
                        }).show();
            }
        });

        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view = View.inflate(getContext(), R.layout.dialog, null);
                init_dlg(view);
                modifyText();
                AlertDialog.Builder dlg = new AlertDialog.Builder(getContext());
                dlg.setTitle("정보수정하기")
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
                                    if (t1.getText().toString().equals("사과 테이블")) {
                                        SaveData(b1, d1, S, P, cost, 1);
                                    } else if (t1.getText().toString().equals("포도 테이블")) {
                                        SaveData(b2, d2, S, P, cost, 1);
                                    } else if (t1.getText().toString().equals("키위 테이블")) {
                                        SaveData(b3, d3, S, P, cost, 1);
                                    } else if (t1.getText().toString().equals("자몽 테이블")) {
                                        SaveData(b4, d4, S, P, cost, 1);
                                    }
                                    Snackbar.make(fragview, "수정되었습니다.", Snackbar.LENGTH_SHORT).show();
                                }
                            }
                        }).show();
            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (t1.getText().toString().equals("사과 테이블")) {
                    ResetData(b1, d1);
                } else if (t1.getText().toString().equals("포도 테이블")) {
                    ResetData(b2, d2);
                } else if (t1.getText().toString().equals("키위 테이블")) {
                    ResetData(b3, d3);
                } else if (t1.getText().toString().equals("자몽 테이블")) {
                    ResetData(b4, d4);
                }
                Snackbar.make(fragview, "초기화되었습니다.", Snackbar.LENGTH_SHORT).show();
            }
        });
        return fragview;
    }

    void SetText(Data d) {
        t1.setText(d.tableName);
        t2.setText(d.time);
        t3.setText(d.spaghetti + "개");
        t4.setText(d.pizza + "개");
        t5.setText(d.membership);
        t6.setText(d.result + "원");
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    void SaveData(Button b, Data d, String S, String P, int cost, int key) {
        d.spaghetti = Integer.parseInt(S);
        if (key == 0)
            d.time = d.Currtime();
        else{
        }
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
        ButtonName(b, d);
        ButtonEnable(d);
    }

    void ResetData(Button b, Data d) {
        d.spaghetti = 0;
        d.pizza = 0;
        d.membership = "";
        d.result = 0;
        t2.setText("");
        t3.setText("");
        t4.setText("");
        t5.setText("");
        t6.setText("");
        ButtonName(b, d);
        ButtonEnable(d);
        b5.setEnabled(true);
        t2.setText("");
    }

    void modifyText() {
        if (t1.getText().toString().equals("사과 테이블")) {
            e1.setText(d1.spaghetti + "");
            e2.setText(d1.pizza + "");
            if(d1.membership.equals("기본멤버쉽")) {
                r2.setChecked(true);
            }
            else if(d1.membership.equals("VIP멤버쉽")) {
                r3.setChecked(true);
            }
        } else if (t1.getText().toString().equals("포도 테이블")) {
            e1.setText(d2.spaghetti + "");
            e2.setText(d2.pizza + "");
            if(d2.membership.equals("기본멤버쉽")) {
                r2.setChecked(true);
            }
            else if(d2.membership.equals("VIP멤버쉽")) {
                r3.setChecked(true);
            }
        } else if (t1.getText().toString().equals("키위 테이블")) {
            e1.setText(d3.spaghetti + "");
            e2.setText(d3.pizza + "");
            if(d3.membership.equals("기본멤버쉽")) {
                r2.setChecked(true);
            }
            else if(d3.membership.equals("VIP멤버쉽")) {
                r3.setChecked(true);
            }
        } else if (t1.getText().toString().equals("자몽 테이블")) {
            e1.setText(d4.spaghetti + "");
            e2.setText(d4.pizza + "");
            if(d4.membership.equals("기본멤버쉽")) {
                r2.setChecked(true);
            }
            else if(d4.membership.equals("VIP멤버쉽")) {
                r3.setChecked(true);
            }
        }
    }

    void ButtonName(Button b, Data d) {
        if (d.result == 0) {
            b.setText(d.tableName.concat("(비어있음)"));
        } else {
            b.setText((d.tableName).substring(0, 6));
        }
    }

    void ButtonEnable(Data d) {
        if (d.result == 0) {
            b6.setEnabled(false);
            b7.setEnabled(false);
        } else {
            b6.setEnabled(true);
            b7.setEnabled(true);
        }
    }

    void ButtonClick(final Button b, final Data d) {
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b5.setEnabled(true);
                ButtonName(b, d);
                SetText(d);
                ButtonEnable(d);
                if (d.result == 0) {
                    t2.setText("");
                    t3.setText("");
                    t4.setText("");
                    t6.setText("");
                    Toast.makeText(getContext(), "비어있는 테이블입니다.", Toast.LENGTH_LONG).show();
                } else {
                    b5.setEnabled(false);
                }
            }
        });
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    void init(View v) {
        b1 = (Button) v.findViewById(R.id.b1);
        b2 = (Button) v.findViewById(R.id.b2);
        b3 = (Button) v.findViewById(R.id.b3);
        b4 = (Button) v.findViewById(R.id.b4);
        b5 = (Button) v.findViewById(R.id.order);
        b6 = (Button) v.findViewById(R.id.modify);
        b7 = (Button) v.findViewById(R.id.reset);


        t1 = (TextView) v.findViewById(R.id.table);
        t2 = (TextView) v.findViewById(R.id.time);
        t3 = (TextView) v.findViewById(R.id.spaghetti);
        t4 = (TextView) v.findViewById(R.id.pizza);
        t5 = (TextView) v.findViewById(R.id.membership);
        t6 = (TextView) v.findViewById(R.id.cost);

        d1 = new Data("사과 테이블", "", 0, 0, "", 0);
        d2 = new Data("포도 테이블", "", 0, 0, "", 0);
        d3 = new Data("키위 테이블", "", 0, 0, "", 0);
        d4 = new Data("자몽 테이블", "", 0, 0, "", 0);
    }

    void init_dlg(View view) {
        e1 = (EditText) view.findViewById(R.id.spaghettiedit);
        e2 = (EditText) view.findViewById(R.id.pizzaedit);
        r1 = (RadioButton) view.findViewById(R.id.r1);
        r2 = (RadioButton) view.findViewById(R.id.r2);
        r3 = (RadioButton) view.findViewById(R.id.r3);
    }

}
