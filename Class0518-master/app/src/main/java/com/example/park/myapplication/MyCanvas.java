package com.example.park.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Park on 2017-05-18.
 */

public class MyCanvas extends View {
    Rect rect;
    public MyCanvas(Context context) {
        super(context);
    }

    public MyCanvas(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.RED);

        rect = new Rect(10,10,100,100);
//        canvas.drawRect(10, 10, 100, 100, paint);
        canvas.drawRect(rect, paint);

        int width = canvas.getWidth()/2-45; //캔버스 전체 폭 = getWidth
        int height = canvas.getHeight()/2-45; //캔버스 전체 높이 = getHeight
        paint.setStyle(Paint.Style.STROKE); //테두리만
        paint.setTextSize(70);
        canvas.drawText("Click Me !!", 300, 300, paint);
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(50);
        canvas.drawRect(width,height, width+90, height+90, paint);

        Bitmap img = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        canvas.drawBitmap(img, 300, 350, paint);
        canvas.drawBitmap(img, 400, 150, paint);

        Bitmap smallimg = Bitmap.createScaledBitmap(img, img.getWidth()/2, img.getHeight()/2, false);
        canvas.drawBitmap(smallimg, 400, 350, paint);

        Bitmap bigimg = Bitmap.createScaledBitmap(img, img.getWidth()*2, img.getHeight()*2, false);
        canvas.drawBitmap(bigimg, 500, 350, paint);

        img.recycle();
        super.onDraw(canvas);
    } // setAntiAlias 원을 부드럽게 그릴떄 true를 줌

    @Override
    public boolean onTouchEvent(MotionEvent event) {
            int x = (int) event.getX();
            int y = (int) event.getY();

//        if(rect.contains(x, y))
        if(x >= 10 && x <=100 && y >=10 && y <=100) { //red rect
            Toast.makeText(getContext(), "RED BUTTON", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(getContext(), "background!", Toast.LENGTH_LONG).show();
        }
        return true; // true 해줘야 이벤트를 받는다.
    }
}
