package com.example.park.myapplication;

import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
public class MainActivity extends AppCompatActivity  implements Fragment1.selectListner  {
    private int table;
    @Override
    public void selected(int i) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.f2,new Fragment2().newInstance(table));
        Log.d("Table2",table+"");
        ft.commit();
        Log.d("Table3",i+"");
        table = i;
        Log.d("Table4",table+"");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("Table5",table+"");

    }

}
