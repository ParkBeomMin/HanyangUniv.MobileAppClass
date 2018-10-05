package com.example.park.myapplication;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import android.widget.CheckBox;

import java.util.ArrayList;

/**
 * Created by Park on 2017-04-06.
 */

public class Data implements Parcelable {
    String name;
    String call;
    String[] menu = new String[3];
    String homepage;
    String date;
    int category;
    int check;
    boolean c1;
    View v;

    public Data(String name, String call, String[] menu, String homepage, String date, int category, int check, boolean c1) {
        this.name = name;
        this.call = call;
        this.menu = menu;
        this.homepage = homepage;
        this.date = date;
        this.category = category;
        this.check = check;
        this.c1 = c1;
    }

    protected Data(Parcel in) {
        name = in.readString();
        call = in.readString();
        menu = in.createStringArray();
        homepage = in.readString();
        date = in.readString();
        category = in.readInt();
        check = in.readInt();
    }

    @Override
    public String toString() {
        return name;
    }

    public static final Creator<Data> CREATOR = new Creator<Data>() {
        @Override
        public Data createFromParcel(Parcel in) {
            return new Data(in);
        }

        @Override
        public Data[] newArray(int size) {
            return new Data[size];
        }
    };

    public void SetData(String name, String call, String[] menu, String homepage, String date, int category, int check) {
        this.name = name;
        this.call = call;
        this.menu = menu;
        this.homepage = homepage;
        this.date = date;
        this.category = category;
        this.check = check;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(call);
        parcel.writeStringArray(menu);
        parcel.writeString(homepage);
        parcel.writeString(date);
        parcel.writeInt(category);
        parcel.writeInt(check);
    }

    public String getName() {
        return name;
    }

    public String getCall() {
        return call;
    }

    public String[] getMenu() {
        return menu;
    }

    public String getHomepage() {
        return homepage;
    }

    public String getDate() {
        return date;
    }

    public int getCategory() {
        return category;
    }

    public int getCheck() {
        return check;
    }

    public View getView() {
        return v;
    }

    public void setV(View v) {
        this.v = v;
    }
}