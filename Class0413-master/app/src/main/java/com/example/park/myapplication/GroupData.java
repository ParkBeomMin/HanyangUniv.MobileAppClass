package com.example.park.myapplication;

import java.util.ArrayList;

/**
 * Created by Park on 2017-04-13.
 */

public class GroupData {
    String groupname;
    ArrayList<String> childlist;

    public GroupData(String groupname) {
        this.groupname = groupname;
        this.childlist = new ArrayList<String>();
    }
}
