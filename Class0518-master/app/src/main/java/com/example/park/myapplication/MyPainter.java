package com.example.park.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Park on 2017-05-18.
 */

public class MyPainter extends View {
    Bitmap mBitmap;
    Canvas mCanvas;
    Paint mPaint = new Paint();

    public MyPainter(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint.setColor(Color.BLACK);
    }

    public MyPainter(Context context) {
        super(context);
        mPaint.setColor(Color.BLACK);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mBitmap != null)
            canvas.drawBitmap(mBitmap, 0, 0, mPaint);
        super.onDraw(canvas);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) { // w, h 현재
        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888); // 비어있는 빕맵 개체 생성 메모리상
        mCanvas = new Canvas();
        mCanvas.setBitmap(mBitmap); // 캔버스에 쓰는게 빕맵에 그려지게 됨
        mCanvas.drawColor(Color.YELLOW);
        drawStamp();
        super.onSizeChanged(w, h, oldw, oldh);
    }

    private void drawStamp() {
        Bitmap img = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        mCanvas.drawBitmap(img, 10, 10, mPaint);
    }

    int oldX = -1, oldY = -1;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int X = (int) event.getX();
        int Y = (int) event.getY();
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            oldX = X;
            oldY = Y;
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            if (oldX != -1) {
                mCanvas.drawLine(oldX, oldY, X, Y, mPaint);
                invalidate();
                oldX = X;
                oldY = Y;
            }
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            if (oldX != -1) {
                mCanvas.drawLine(oldX, oldY, X, Y, mPaint);
                invalidate();
            }
            oldX = -1;
            oldY = -1;
        }
        return true;
    }
}
