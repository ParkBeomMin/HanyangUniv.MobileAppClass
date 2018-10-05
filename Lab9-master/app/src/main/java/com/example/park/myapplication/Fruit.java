package com.example.park.myapplication;

/**
 * Created by Park on 2017-04-27.
 */

public class Fruit {

    String name;
    String price;
    int imgno;  //IMAGE_ID
    int checked;

    public int getChecked() {
        return checked;
    }

    public int getImgno() {
        return imgno;
    }

    public String getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Fruit{" +
                "name='" + name + '\'' +
                '}';
    }

    public void setChecked(int checked) {
        this.checked = checked;
    }

    public Fruit(String name, int imgno, String price, int checked) {
        this.name = name;
        this.imgno = imgno;
        this.price = price;
        this.checked = checked;
    }
}
