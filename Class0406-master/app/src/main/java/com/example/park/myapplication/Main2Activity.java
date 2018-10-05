package com.example.park.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
TextView t1;
    Button b1;
    EditText e1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        t1 = (TextView) findViewById(R.id.textView2);
        b1 = (Button) findViewById(R.id.button2);
        e1 = (EditText) findViewById(R.id.editText2);
        Intent intent = getIntent();
        String a = intent.getStringExtra("name");
        Student sss = intent.getParcelableExtra("student1");
        String str = sss.toString();
        t1.setText(str);
        e1.setText(a);

    }

    public void onClick(View view) {
        if(view == b1) {
            Intent intent = new Intent();
            intent.putExtra("remake", e1.getText().toString());
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}
