package com.example.park.myapplication;

import android.content.ClipData;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionProvider;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Canvas canvas;
    CheckBox stampCheck;
    Button b1, b2, b3, b4;
MenuItem menuItem1, menuItem2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkPermission();
        b1 = (Button) findViewById(R.id.rotate);
        b2 = (Button) findViewById(R.id.move);
        b3 = (Button) findViewById(R.id.scale);
        b4 = (Button) findViewById(R.id.skew);
        canvas = (Canvas) findViewById(R.id.canvas);
        stampCheck = (CheckBox) findViewById(R.id.stampCheck);
        stampCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    canvas.setStamp(1);
                } else {
                    canvas.setStamp(0);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 1, 0, "Bluring").setCheckable(true);
        menu.add(0, 2, 0, "Coloring").setCheckable(true);
        menu.add(0, 3, 0, "Pen Width Big").setCheckable(true);
        menu.add(0, 4, 0, "Pen Color RED");
        menu.add(0, 5, 0, "Pen Color BLUE");

        menuItem1 = menu.findItem(1);menuItem2 = menu.findItem(2);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == 1) {
            if (!item.isChecked()) {
                canvas.setBlur(1);
                item.setChecked(true);
            } else {
                canvas.setBlur(0);
                item.setChecked(false);
            }
        } else if (item.getItemId() == 2) {
            if (!item.isChecked()) {
                canvas.setColor(1);
                item.setChecked(true);
            } else {
                canvas.setColor(0);
                item.setChecked(false);
            }
        } else if (item.getItemId() == 3) {
            if (!item.isChecked()) {
                canvas.setPenSize(5);
                item.setChecked(true);
            } else if (item.isChecked()) {
                canvas.setPenSize(3);
                item.setChecked(false);
            }
        } else if (item.getItemId() == 4) {
            canvas.setPenColor(Color.RED);
        } else if (item.getItemId() == 5) {
            canvas.setPenColor(Color.BLUE);
        }
        return super.onOptionsItemSelected(item);
    }

    int Ro, Mo, Sc, Sk = 0;

    public void onClick(View v) {
        if (v.getId() == R.id.eraser) {
            canvas.setBlur(0);
            canvas.setColor(0);
            menuItem1.setChecked(false);
            menuItem2.setChecked(false);
            canvas.clear();
        } else if (v.getId() == R.id.open) {
            canvas.setBlur(0);
            canvas.setColor(0);
            menuItem1.setChecked(false);
            menuItem2.setChecked(false);
            canvas.clear();
            canvas.Open(getExternalPath() + "MyCanvas.jpeg");
        } else if (v.getId() == R.id.save) {
            Log.d("BEOM4", "저장버튼눌림" + getExternalPath());
            if (canvas.Save(getExternalPath() + "MyCanvas.jpeg")) {
                Toast.makeText(getApplicationContext(), "저장완료!", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), "저장실패!", Toast.LENGTH_LONG).show();
            }
        } else if (v.getTag().equals("rotate")) {
            if (Ro == 0) {
                stampCheck.setChecked(true);
                canvas.setRotate("rotate");
                b1.setText("Using");
                Ro++;
            } else {
                stampCheck.setChecked(false);
                canvas.setRotate("clear");
                b1.setText("ROTATE");
                Ro = 0;
            }
        } else if (v.getTag().equals("move")) {
            if (Mo == 0) {
                stampCheck.setChecked(true);
                canvas.setMove("move");
                b2.setText("Using");
                Mo++;
            } else {
                canvas.setMove("clear");
                stampCheck.setChecked(false);
                b2.setText("MOVE");
                Mo = 0;
            }
        } else if (v.getTag().equals("scale")) {
            if (Sc == 0) {
                stampCheck.setChecked(true);
                canvas.setScale("scale");
                Sc++;
                b3.setText("Using");
            } else {
                canvas.setScale("clear");
                stampCheck.setChecked(false);
                Sc = 0;
                b3.setText("SCALE");
            }
        } else if (v.getTag().equals("skew")) {
            if (Sk == 0) {
                stampCheck.setChecked(true);
                canvas.setSkew("skew");
                Sk++;
                b4.setText("Using");
            } else {
                canvas.setSkew("clear");
                stampCheck.setChecked(false);
                Sk = 0;
                b4.setText("SKEW");
            }
        }
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
            Toast.makeText(getApplicationContext(), "SDCard 쓰기 권한 있음", Toast.LENGTH_SHORT).show();
        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                Toast.makeText(getApplicationContext(), "권한의 필요성 설명", Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
            }
        }
    }
}
