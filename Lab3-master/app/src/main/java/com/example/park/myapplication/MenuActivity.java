package com.example.park.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import static android.R.attr.bitmap;

public class MenuActivity extends AppCompatActivity {
    ImageView i1, i2;
    TextView t1 ;
    RelativeLayout r1;
    int degree1 = 30;
    int degree2 = 30;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        setTitle("메뉴를 눌러보세요");
        init();
    }

    void init() {
        i1 = (ImageView) findViewById(R.id.Chikenimage);
        i2 = (ImageView) findViewById(R.id.Spaghettiimage);
        t1 = (TextView) findViewById(R.id.foodname);
        r1 = (RelativeLayout) findViewById(R.id.activity_menu);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.next) {
             Intent intent = new Intent(this, CalculActivity.class);
               startActivity(intent);
        }
        else if(item.getItemId() == R.id.mnuRed) {
            r1.setBackgroundColor(Color.RED);
        }
        else if(item.getItemId() == R.id.mnuBlue) {
            r1.setBackgroundColor(Color.BLUE);
        }
        else if(item.getItemId() == R.id.mnuYellow) {
            r1.setBackgroundColor(Color.YELLOW);
        }
        else if(item.getItemId() == R.id.mnuSpin) {
            Rotate();
        }
        else if(item.getItemId() == R.id.mnuShow) {
             item.setChecked(!item.isChecked());
            if(item.isChecked()) {
                t1.setVisibility(View.VISIBLE);
            }
            else {
                t1.setVisibility(View.INVISIBLE);
            }
            if(item.isChecked()) {
                ShowName();
            }
        }
        else if(item.getItemId() == R.id.mnuDouble) {
            item.setChecked(!item.isChecked());
            //Check(item, item.isChecked());
            DoubleScale(item.isChecked());
        }
        else if(item.getItemId() == R.id.mnuChiken) {
            i1.setVisibility(View.VISIBLE);
            i2.setVisibility(View.INVISIBLE);
            ShowName();
            item.setChecked(true);
        }
        else if(item.getItemId() == R.id.mnuSpaghetti) {
            i1.setVisibility(View.INVISIBLE);
            i2.setVisibility(View.VISIBLE);
            ShowName();
            item.setChecked(true);
        }
        return super.onOptionsItemSelected(item);
    }

    void ShowName() {
          if(i1.getVisibility() == View.VISIBLE) {
                  Log.d("Beom","1");
                t1.setText("맛있는 치킨!");
            }
        else if(i2.getVisibility() == View.VISIBLE) {
                t1.setText("맛있는 스파게티!");
            }
    }

//    void Check(MenuItem i, boolean b) {
//        if(b == true) {
//            i.setChecked(false);
//        }
//        else {
//            i.setChecked(true);
//        }
//    }

    void DoubleScale(boolean b) {
        if(b == true) {
                i1.setScaleX((float) Math.sqrt(2));
                i1.setScaleY((float) Math.sqrt(2));
                i2.setScaleX((float) Math.sqrt(2));
                i2.setScaleY((float) Math.sqrt(2));
            }
        else {
                i1.setScaleX(1);
                i1.setScaleY(1);
                i2.setScaleX(1);
                i2.setScaleY(1);
            }
        }

    void Rotate() {
        if(i1.getVisibility() == View.VISIBLE) {
            i1.setRotation(degree1);
            degree1 += 30;
        }
        else if(i2.getVisibility() == View.VISIBLE) {
            i2.setRotation(degree2);
            degree2 += 30;
        }
    }
}
