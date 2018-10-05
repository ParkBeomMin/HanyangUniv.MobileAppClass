package com.example.park.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{// implements View.OnClickListener {
    ArrayList<Fruit> fruit = new ArrayList<Fruit>();
    GridAdapter adapter;
    GridView gridView;
    CheckBox c1;
    AutoCompleteTextView et1;
    EditText et2;
    ImageView img;
    Button b_add, b_next;
    int positon;
    ArrayList<String> fruitName = new ArrayList<String>();
    ArrayAdapter<String> auto;
    int img_set;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("What is your favorite fruite/?");
        init();

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplication(), fruit.get(i).getName() + "이 카트에 담겼습니다.", Toast.LENGTH_LONG).show();
                Log.d("Park", fruit.get(i).getName() + fruit.get(i).getImgno() + fruit.get(i).getPrice() + "!!!");
                et1.setText(fruit.get(i).getName());
                et2.setText(fruit.get(i).getPrice());
                img.setImageResource(fruit.get(i).getImgno());
                b_add.setText("M");
                positon = i;
                img_set = fruit.get(i).getImgno();
            }
        });
        final AddFruit addFruit = (AddFruit) findViewById(R.id.add);
        addFruit.setOnAddListener(new AddFruit.OnAddListener() {
            @Override
            public void onAdd(String name, int imageno, String price) {
                Toast.makeText(getApplicationContext(), name + "이 추가되었습니다.", Toast.LENGTH_LONG).show();
                fruitName.add(name);
                auto.notifyDataSetChanged();
                Log.d("Pakr1", fruitName.get(0).toString());
                if (c1.isChecked())
                    adapter.addFruit(new Fruit(name, FRUITDATA.imglist[imageno], price, 1));
                else
                    adapter.addFruit(new Fruit(name, FRUITDATA.imglist[imageno], price, 0));
                et1.setText("");
                et2.setText("");
                et1.requestFocus();
                adapter.refresh();
            }
        });
        addFruit.setOnMoifyListener(new AddFruit.OnModifyListener() {
            @Override
            public void onModify(String name, int imageno, String price) {
                fruitName.set(positon, name);
                auto.notifyDataSetChanged();
                if (c1.isChecked())
                    fruit.set(positon, new Fruit(name, FRUITDATA.imglist[imageno], price, 1));
                else
                    fruit.set(positon, new Fruit(name, FRUITDATA.imglist[imageno], price, 0));
                b_add.setText("ADD");
                adapter.refresh();
            }
        });
        c1 = (CheckBox) findViewById(R.id.checkbox);
        c1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                for (int i = 0; i < fruit.size(); i++) {
                    if (b) {
                        fruit.get(i).setChecked(1);
                    } else {
                        fruit.get(i).setChecked(0);
                    }
                    adapter.refresh();
                }
            }
        });
    }

    void init() {
        et1 = (AutoCompleteTextView) findViewById(R.id.f_name);
        et2 = (EditText) findViewById(R.id.f_price);
        img = (ImageView) findViewById(R.id.image1);
        gridView = (GridView) findViewById(R.id.grid);
        b_add = (Button) findViewById(R.id.b_add);
        b_next = (Button) findViewById(R.id.b_next);
//        b_next.setOnClickListener(this);
        adapter = new GridAdapter(this, fruit);
        gridView.setAdapter(adapter);
        fruitName.add("아보카도");
        fruitName.add("수박");
        fruitName.add("오렌지");
        fruitName.add("키위");
        fruitName.add("체리");
        fruitName.add("라즈베리");
        auto = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, fruitName);
        et1.setAdapter(auto);
    }
}
