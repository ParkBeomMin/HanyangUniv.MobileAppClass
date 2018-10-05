package com.example.park.myapplication;

/**
 * Created by Park on 2017-04-27.
 */

public class Fruit {



    String name;
    int imgno;  //IMAGE_ID

    public int getImgno() {
        return imgno;
    }

    public String getName() {
        return name;
    }

    public Fruit(String name, int imgno) {
        this.name = name;
        this.imgno = imgno;
    }
}
