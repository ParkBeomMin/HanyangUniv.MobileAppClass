package com.example.park.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {
    ListView i1;
    EditText e1;
    ArrayList<String> data = new ArrayList<String>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    void init() {
        i1 = (ListView) findViewById(R.id.l1);
        e1 = (EditText) findViewById(R.id.addText);
    e1.addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            String search = editable.toString();
            if(search.length() > 0) {
                i1.setFilterText(search);
            }
            else {
                i1.clearTextFilter();
            }
        }
    });

        data.add("사과");
        data.add("토마토");
        data.add("오렌지");
        data.add("사과");
        data.add("토마토");
        data.add("오렌지");
        data.add("사과");
        data.add("토마토");
        data.add("오렌지");
//        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);
//        adapter.insert("키위",data.size()); //어댑터를 이용해서 데이터추가를 할수있다. 노티퐈이 안해도됨. 두번째인자는 위치할 인덱스
//        i1.setAdapter(adapter);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, data);
        i1.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        i1.setAdapter(adapter);
    }

    public void MyOnClick(View v) {
        if (v.getId() == R.id.sortbutton) {
            Collections.sort(data, nameAsc);
        } else if (v.getId() == R.id.addbutton) {
            data.add(e1.getText().toString());
            adapter.notifyDataSetChanged();
        }
    }

    Comparator<String> nameAsc = new Comparator<String>() {
        @Override
        public int compare(String s, String t1) {
            return s.compareTo(t1); //s 와 t1의 순서를 바꾸면 순서가 바뀐다.
        }
    };
}
