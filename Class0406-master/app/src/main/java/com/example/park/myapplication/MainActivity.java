package com.example.park.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button b1;
    EditText e1;
    TextView t1;
    final int _REQUEST_MSG_CODE = 10;
    static final int PICK_CONTACT_REQUEST = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 = (Button) findViewById(R.id.button);
        e1 = (EditText) findViewById(R.id.editText);
        t1 = (TextView) findViewById(R.id.textView);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == _REQUEST_MSG_CODE) {
            if (resultCode == RESULT_OK) {
                String mag = data.getStringExtra("remake");
                t1.setText(mag);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.Pbtn) {
            Intent intent = new Intent(this, Main2Activity.class);
            Student s1 = new Student("박범민", "2013043218", 24, 1);
            intent.putExtra("student1", s1);
            startActivityForResult(intent, 100);
        } else if (view.getId() == R.id.bbbb) {
            Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
            startActivityForResult(intent, PICK_CONTACT_REQUEST);
        } else if (view.getId() == R.id.button) {
            Intent intent = new Intent(this, Main2Activity.class);
            intent.putExtra("name", e1.getText().toString());
            startActivityForResult(intent, _REQUEST_MSG_CODE);
        }else if (view.getId() == R.id.callbutton) {
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:/01089519523"));
            startActivity(intent);
        }
    }
}
