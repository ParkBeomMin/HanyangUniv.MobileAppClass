package com.example.park.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Park on 2017-04-06.
 */

public class Student implements Parcelable {
    private String name = "";
    private String hakno = "";
    private int age = 0;
    private int isman = 1;

    public Student(String name, String hakno, int age, int isman) {
        this.name = name;
        this.hakno = hakno;
        this.age = age;
        this.isman = isman;
    }

    @Override
    public String toString() {
        String str= name + ":" + hakno + ":" + age + ":" + isman;
        return  str;
    }

    protected Student(Parcel in) {
        name = in.readString();
        hakno = in.readString();
        age = in.readInt();
        isman = in.readInt();
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    public void setStudent(String name, String hakno, int age, int isman) {
        this.name = name;
        this.hakno = hakno;
        this.age = age;
        this.isman = isman;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(hakno);
        parcel.writeInt(age);
        parcel.writeInt(isman);
    }
}
