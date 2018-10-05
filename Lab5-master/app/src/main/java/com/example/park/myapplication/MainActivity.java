package com.example.park.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView l1;
    ArrayList<Data> arrayList = new ArrayList<Data>();
    ArrayAdapter adapter;
    TextView t1;
    Data d;
private int DATA_CODE = 0;
    private int POSITION_CODE = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("나의 맛집");
        init();
    }

    void init() {
        l1 = (ListView) findViewById(R.id.listview);
        t1 = (TextView) findViewById(R.id.tv);

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
        l1.setAdapter(adapter);
        l1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, Main3Activity.class);
                intent.putExtra("position",arrayList.get(i));
                Log.d("Park",arrayList.get(i).name);
                startActivityForResult(intent, POSITION_CODE);
            }
        });
        l1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final int position = i;
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("정보삭제")
                        .setMessage("정말 삭제하시겠습니까?")
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(getApplicationContext(), "삭제되었습니다.", Toast.LENGTH_LONG).show();
                                arrayList.remove(position);
                                adapter.notifyDataSetChanged();
                                t1.setText("맛집 리스트("+arrayList.size()+"개)");
                            }
                        })
                        .setNegativeButton("취소", null)
                        .show();
                return true;
            }
        });


    }

    public void onClick(View v) {
        if (v.getId() == R.id.Addbutton) {
            Intent intent = new Intent(this, Main2Activity.class);
            intent.putExtra("data",d);
            startActivityForResult(intent, DATA_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == DATA_CODE) {
            if(resultCode == RESULT_OK) {
                d = data.getParcelableExtra("data");
                Log.d("ARR2",d.menu[0]);
                arrayList.add(d);
                adapter.notifyDataSetChanged();
                t1.setText("맛집 리스트("+arrayList.size()+"개)");
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
