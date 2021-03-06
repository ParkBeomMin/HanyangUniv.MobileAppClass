package com.example.park.togetherclass;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Park on 2017-06-02.
 */

public class NoticeAddRequest extends StringRequest {
    final static private String URL = "http://pkr10.cafe24.com/NoticeWrite.php";

    private Map<String, String> parameters;

    public NoticeAddRequest(String NoticeTitle, String NoticeContent, String NoticeName, String NoticeDate, String NoticePw, String NoticeSubject, Response.Listener<String> listener) {
        super(Request.Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("NoticeTitle", NoticeTitle);
        parameters.put("NoticeContent", NoticeContent);
        parameters.put("NoticeName", NoticeName);
        parameters.put("NoticeDate", NoticeDate);
        parameters.put("NoticePw", NoticePw);
        parameters.put("NoticeSubject", NoticeSubject);
    }

    public Map<String, String> getParams() {
        return parameters;
    }
}

