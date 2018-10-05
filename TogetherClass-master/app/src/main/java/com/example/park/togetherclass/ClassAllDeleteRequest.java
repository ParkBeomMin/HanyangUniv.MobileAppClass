package com.example.park.togetherclass;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Park on 2017-06-05.
 */

public class ClassAllDeleteRequest extends StringRequest {
    final static private String URL = "http://pkr10.cafe24.com/ClassAllDelete.php";

    private Map<String, String> parameters;


    public ClassAllDeleteRequest(Response.Listener<String> listener) {
        super(Request.Method.POST, URL, listener, null);
        parameters = new HashMap<>();
    }

    public Map<String, String> getParams() {
        return parameters;
    }
}

