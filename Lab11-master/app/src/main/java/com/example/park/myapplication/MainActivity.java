package com.example.park.myapplication;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private long lastTimeBackPressed;
    LinearLayout l1, l2;
    ListView listView;
    EditText e1;
    DatePicker d1;
    String MYPATH = "";
    String MODIFYFILE = "";
    int MODIFYPOSITION;
    ArrayList<String> arrayList = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    Button b1;
    TextView t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MYPATH = getExternalPath() + "diary";
        checkPermission();
        makeDir();
        init();
        ListViewMethod();
//        makeDir();
//        LoadList();
    }

    void init() {
        t1 = (TextView) findViewById(R.id.tvCount);
        t1.setText("등록된 메모 개수:" + arrayList.size());
        b1 = (Button) findViewById(R.id.btnsave);
        e1 = (EditText) findViewById(R.id.MemoEt);
        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, arrayList);
        listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        d1 = (DatePicker) findViewById(R.id.datePicker);
        d1.init(d1.getYear(), d1.getMonth(), d1.getDayOfMonth(), new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                CheckDate(i, i1 + 1, i2);
            }
        });

        l1 = (LinearLayout) findViewById(R.id.linear1);
        l2 = (LinearLayout) findViewById(R.id.linear2);
    }

    void ListViewMethod() {
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(final AdapterView<?> adapterView, View view, int i, long l) {
                final int position = i;
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("삭제")
                        .setMessage("정말 삭제하시겠습니까?")
                        .setNegativeButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                File file = new File(MYPATH + "/" + arrayList.get(position).toString());
                                file.delete();
                                arrayList.remove(position);
                                adapter.notifyDataSetChanged();
                                t1.setText("등록된 메모 개수:" + arrayList.size());
                            }
                        })
                        .setPositiveButton("취소", null)
                        .show();
                return true;
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                l1.setVisibility(View.GONE);
                l2.setVisibility(View.VISIBLE);
                b1.setText("수정");
                MODIFYFILE = arrayList.get(i).toString();
                MODIFYPOSITION = i;
                String date = "20" + arrayList.get(i).toString().substring(0, arrayList.get(i).toString().length() - 4);
                d1.updateDate(Integer.parseInt(date.substring(0, 4)), Integer.parseInt(date.substring(5, 7)) - 1, Integer.parseInt(date.substring(8, 10)));
                Log.d("Park", date.substring(0, 4) + "년" + date.substring(5, 7) + "월" + date.substring(8, 10));
                try {
                    BufferedReader br = new BufferedReader(new FileReader(MYPATH + "/" + MODIFYFILE));
                    String readStr = "";
                    String str = null;
                    while ((str = br.readLine()) != null)
                        readStr += str + "\n"; // readLine -> 엔터나올때까지
                    br.close();
                    e1.setText(readStr);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, "File not found", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnsave:
                String msg = "저장완료!";
                if (b1.getText() == "수정") {
                    msg = "수정완료!";
                    File file = new File(MYPATH + "/" + MODIFYFILE);
                    file.delete();
                    arrayList.remove(MODIFYPOSITION);
                }
                String year = d1.getYear() + "";
                String month = "0" + (d1.getMonth() + 1) + "";
                if (month.length() == 3) month = month.substring(1, 3);
                String day = "0" + d1.getDayOfMonth() + "";
                if (day.length() == 3) day = day.substring(1, 3);
                String FileName = year.substring(2, 4) + "-" + month + "-" + day + ".txt";
                try {
                    BufferedWriter bw = new BufferedWriter(new FileWriter(MYPATH + "/" +
                            FileName, false)); // true : 어펜딩, 붙어서 계속 저장 / false : 새로 만듦
                    bw.write("");
                    bw.write(e1.getText().toString());
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
                arrayList.add(FileName);
                setVisible(0);
                adapter.notifyDataSetChanged();
                t1.setText("등록된 메모 개수:" + arrayList.size());
                Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
                d1.updateDate(d1.getYear(), d1.getMonth(), d1.getDayOfMonth() + 1);
                Collections.sort(arrayList, comparator);
                break;
            case R.id.btncancel:
                setVisible(0);
                d1.updateDate(d1.getYear(), d1.getMonth(), d1.getDayOfMonth() + 1);
                break;
            case R.id.btn1:
                setVisible(1);
                CheckDate(d1.getYear(), d1.getMonth() + 1, d1.getDayOfMonth());
                break;
        }
    }

    void CheckDate(int y, int m, int d) {
        String year = y + "";
        String month = "0" + (m) + "";
        if (month.length() == 3) month = month.substring(1, 3);
        String day = "0" + d + "";
        if (day.length() == 3) day = day.substring(1, 3);
        String date = year + "-" + month + "-" + day;
        Log.d("Park1", date);
        String compare = date.substring(2, date.length());
        Log.d("Park2", compare);
        for (int p = 0; p < arrayList.size(); p++) {
            if (arrayList.get(p).toString().substring(0, arrayList.get(p).length() - 4).equals(compare)) {
                MODIFYFILE = arrayList.get(p).toString();
                MODIFYPOSITION = p;
                Toast.makeText(getApplicationContext(), "수정모드입니다.", Toast.LENGTH_LONG).show();
                b1.setText("수정");
                try {
                    BufferedReader br = new BufferedReader(new FileReader(MYPATH + "/" + arrayList.get(p).toString()));
                    String readStr = "";
                    String str = null;
                    try {
                        while ((str = br.readLine()) != null)
                            readStr += str + "\n"; // readLine -> 엔터나올때까지
                        e1.setText(readStr);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    void setVisible(int op) {
        if (op == 0) {
            l1.setVisibility(View.VISIBLE);
            l2.setVisibility(View.GONE);
            b1.setText("저장");
            e1.setText("");
        } else {
            l1.setVisibility(View.GONE);
            l2.setVisibility(View.VISIBLE);
        }
    }

    void makeDir() {
        File file = new File(MYPATH);
        file.mkdir();
        String msg = "디렉터리생성";

        if (file.isDirectory() == false)
            msg = "디렉터리 생성오류";
        else
            LoadList();
        Log.d("MakeDir", msg);
    }

    public String getExternalPath() {
        String sdPath = "";
        String ext = Environment.getExternalStorageState();
        if (ext.equals(Environment.MEDIA_MOUNTED)) {
            sdPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/";
            Log.d("PATH", sdPath);
        } else {
            sdPath = getFilesDir() + "";
            Toast.makeText(getApplicationContext(), sdPath, Toast.LENGTH_LONG).show();
        }
        return sdPath;
    }

    void checkPermission() {
        int permissioninfo = ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permissioninfo == PackageManager.PERMISSION_GRANTED) {
            Log.d("checkPermission", "SDCard 쓰기 권한 있음");
        } else {
            Log.d("checkPermission", "권한의 필요성 설명");
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
        }
    }


    void LoadList() {
        File[] files = new File(MYPATH).listFiles();
        String str = "";
        for (File f : files) {
            str = f.getName();
            arrayList.add(str);
            str = "";
        }
        Collections.sort(arrayList, comparator);
    }

    @Override
    public void onBackPressed() {
        if (l1.getVisibility() == View.VISIBLE) {
            if (System.currentTimeMillis() - lastTimeBackPressed < 1500) {
                finish();
                return;
            }
            Toast.makeText(this, "뒤로 버튼을 한번 더 누르면 종료됩니다", Toast.LENGTH_SHORT).show();
            lastTimeBackPressed = System.currentTimeMillis();
        } else {
            setVisible(0);
            d1.updateDate(d1.getYear(), d1.getMonth(), d1.getDayOfMonth() + 1);
        }
    }

    Comparator<String> comparator = new Comparator<String>() {
        @Override
        public int compare(String s, String t1) {
            return s.compareTo(t1);
        }
    };
}
