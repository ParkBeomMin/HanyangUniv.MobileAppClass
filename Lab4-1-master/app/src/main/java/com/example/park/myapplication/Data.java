package com.example.park.myapplication;

import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.Date;

/**
 * Created by Park on 2017-03-31.
 */
public class Data {
    String tableName;
    String time;
    int spaghetti, pizza;
    String membership;
    int result;


    @RequiresApi(api = Build.VERSION_CODES.N)
    public Data(String tableName, String time, int spaghetti, int pizza, String membership, int result) {
        this.tableName = tableName;
        this.time = time;
        this.spaghetti = spaghetti;
        this.pizza = pizza;
        this.membership = membership;
        this.result = result;

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    String Currtime() {
        long x = System.currentTimeMillis();
        Date date = new Date(x);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String ftime = sdf.format(date);
        return ftime;
    }
    }