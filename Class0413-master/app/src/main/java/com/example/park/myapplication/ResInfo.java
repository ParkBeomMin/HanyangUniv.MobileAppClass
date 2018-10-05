package com.example.park.myapplication;

/**
 * Created by Park on 2017-04-13.
 */

public class ResInfo {
    private String name;
    private String tel;
    private int imageno = 0;

    public ResInfo(String name, String tel, int imagno) {
        this.name = name;
        this.tel = tel;
        this.imageno = imagno;
    }

    public void setData(String name, String tel, int imagno) {
        this.name = name;
        this.tel = tel;
        this.imageno = imagno;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setImageno(int imageno) {
        this.imageno = imageno;
    }

    public String getName() {
        return name;
    }

    public String getTel() {
        return tel;
    }

    public int getImageno() {
        return imageno;
    }
}
