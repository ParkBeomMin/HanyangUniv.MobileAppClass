package com.example.park.myapplication;

import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.EditText;

import java.util.Date;

/**
 * Created by Park on 2017-03-30.
 */

public class Data{
    String tableName;
    String time;
    int spaghetti, pizza;
    String membership;
    int result;


    @RequiresApi(api = Build.VERSION_CODES.N)
    public Data(String tableName, int spaghetti, int pizza, String membership, int result) {
        this.tableName = tableName;
        this.time = Currtime();
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



    void AppleTable(){

    }

    void GrapeTable(){

    }

    void KiwiTable(){

    }

    void grapefruitTable(){

    }

}
//    String TableName(String i) {
//        if(i == "0") {
//            return "사과 테이블";
//        }
//        else if (i == "1") {
//            return "포도 테이블";
//        }
//        else if (i == "2") {
//            return "키위 테이블";
//
//        }
//        else if (i == "3") {
//            return "자몽 테이블";
//        }
//
//
//}
