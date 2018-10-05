package com.example.park.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView l1;
    Button b1;
    ArrayList<Data> arrayList = new ArrayList<Data>();
    DataAdapter adapter;
    TextView t1;
    Data d;
    EditText e1;
    private int DATA_CODE = 0;
    private int POSITION_CODE = 10;
    private int SORT_NAME = 0;
    private int SORT_KIND = 0;

    private int SIZE = arrayList.size();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("나의 맛집");
        init();
    }

    void init() {
        l1 = (ListView) findViewById(R.id.listview);
        b1 = (Button) findViewById(R.id.selectbutton);
        e1 = (EditText) findViewById(R.id.searchText);
        e1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) { //custom 객체에 대한 검색기능
                String search = editable.toString();
                Log.d("Beom", search);
                if (search.length() != 0) {
                    ArrayList<Data> searchdata = new ArrayList<Data>();
                    DataAdapter searchadapter;
                    for (int j = 0; j < arrayList.size(); j++) {
                        if (arrayList.get(j).getName().contains(search)) {
                            searchdata.add(arrayList.get(j));
                        }
                    }
                    searchadapter = new DataAdapter(MainActivity.this, searchdata);
                    l1.setAdapter(searchadapter);
                } else {
                    adapter = new DataAdapter(MainActivity.this, arrayList);
                    l1.setAdapter(adapter);
                }
            }
        });
        adapter = new DataAdapter(MainActivity.this, arrayList);
        l1.setAdapter(adapter);
        l1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, Main3Activity.class);
                intent.putExtra("position", arrayList.get(i));
                Log.d("Park", arrayList.get(i).name);
                startActivityForResult(intent, POSITION_CODE);
            }
        });
    }

    public void onClick(View v) {
        if (v.getId() == R.id.Addbutton) {
            Intent intent = new Intent(this, Main2Activity.class);
            intent.putExtra("data", d);
            startActivityForResult(intent, DATA_CODE);
        } else if (v.getId() == R.id.namesortbutton) {
            Log.d("NAME", SORT_NAME + "");
            if (SORT_NAME == 0) {
                adapter.setSort(DataAdapter.NAME_ASC);
                SORT_NAME++;
                Toast.makeText(this, "ASC정렬입니다.", Toast.LENGTH_LONG).show();
                Log.d("NAME1", SORT_NAME + "");
            } else {
                adapter.setSort(DataAdapter.NAME_DESC);
                SORT_NAME--;
                Toast.makeText(this, "DESC정렬입니다.", Toast.LENGTH_LONG).show();
                Log.d("NAME2", SORT_NAME + "");
            }

        } else if (v.getId() == R.id.kindsortbutton) {
            if (SORT_KIND == 0) {
                adapter.setSort(DataAdapter.KIND_ASC);
                SORT_KIND++;
                Toast.makeText(this, "ASC정렬입니다.", Toast.LENGTH_LONG).show();
            } else {
                adapter.setSort(DataAdapter.KIND_DESC);
                SORT_KIND--;
                Toast.makeText(this, "DESC정렬입니다.", Toast.LENGTH_LONG).show();
            }

        } else if (v == b1) {
            if (b1.getText().equals("선택")) {
                b1.setText("삭제");
                for (int i = 0; i < arrayList.size(); i++) {
                    arrayList.get(i).check = 1;
                }
            } else {
                for (int i = 0; i < arrayList.size(); i++) {
                    arrayList.get(i).check = 0;
                    View view = arrayList.get(i).getView();
                    CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkbox);
                    if (checkBox.isChecked()) {
                        arrayList.remove(i);
                        i--;
                    }
                }

                b1.setText("선택");
            }
            adapter.notifyDataSetChanged();

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == DATA_CODE) {
            if (resultCode == RESULT_OK) {
                d = data.getParcelableExtra("data");
                Log.d("ARR2", d.menu[0]);
                arrayList.add(d);
                adapter.notifyDataSetChanged();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
