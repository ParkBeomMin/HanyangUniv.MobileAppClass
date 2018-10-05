package com.example.park.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Park on 2017-05-18.
 */

public class MyCanvas2 extends View {

    String operationType="";

    public MyCanvas2(Context context) {
        super(context);
        this.setLayerType(LAYER_TYPE_SOFTWARE, null); // 블러링 하기 위해서.
    }

    public MyCanvas2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        Paint paint = new Paint();
//        paint.setColor(Color.RED);
//        paint.setStyle(Paint.Style.STROKE);
//        paint.setStrokeWidth(5);
//        canvas.drawRect(startX, startY, stopX, stopY, paint);


        Paint paint1 = new Paint();
        Bitmap img = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        Bitmap bigimg = Bitmap.createScaledBitmap(img, img.getWidth() * 2, img.getHeight() * 2, false);
        int cenX = (canvas.getWidth() - bigimg.getWidth()) / 2;
        int cenY = (canvas.getHeight() - bigimg.getHeight()) / 2;
        if (operationType.equals("rotate"))
            canvas.rotate(45, this.getWidth() / 2, this.getHeight() / 2);


//        BlurMaskFilter blur = new BlurMaskFilter(100, BlurMaskFilter.Blur.NORMAL);
//        paint1.setMaskFilter(blur);


        float[] array = {
                2,0,0,0,-25f,
                0,2,0,0,-25f,
                0,0,2,0,-25f,
                0,0,0,2,0
        };
        ColorMatrix colorMatrix = new ColorMatrix(array);
        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(colorMatrix);
        paint1.setColorFilter(filter);

        canvas.drawBitmap(bigimg, cenX, cenY, paint1);

    }

    float startX = -1, startY = -1, stopX = -1, stopY = -1;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == event.ACTION_DOWN) {
            startX = event.getX();
            startY = event.getY();
        } else if (event.getAction() == event.ACTION_UP) {
            stopX = event.getX();
            stopY = event.getY();
            invalidate();
        }
        return true;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
        invalidate();
    }
}
