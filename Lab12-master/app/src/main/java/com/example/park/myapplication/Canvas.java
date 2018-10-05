package com.example.park.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Park on 2017-05-18.
 */

public class Canvas extends View {
    int OP_STAMP;
    int OP_SIZE;
    int OP_PEN_COLOR = Color.BLACK;
    String OP_MOVE = "";
    String OP_SCALE = "";
    Bitmap mBitmap;
    android.graphics.Canvas mCanvas;
    Paint mPaint = new Paint();

    public Canvas(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint.setColor(Color.BLACK);
        this.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    }

    public Canvas(Context context) {
        super(context);
        this.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        mPaint.setColor(Color.BLACK);
    }

    @Override
    protected void onDraw(android.graphics.Canvas canvas) {
        if (mBitmap != null)
            canvas.drawBitmap(mBitmap, 0, 0, null);
        super.onDraw(canvas);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) { // w, h 현재
        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888); // 비어있는 빕맵 개체 생성 메모리상
        mCanvas = new android.graphics.Canvas();
        mCanvas.setBitmap(mBitmap); // 캔버스에 쓰는게 빕맵에 그려지게 됨
        mCanvas.drawColor(Color.YELLOW);

        super.onSizeChanged(w, h, oldw, oldh);
    }

    private void drawStamp(int x, int y) {
        Bitmap img = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        if (OP_SCALE.equals("scale")) {
            Bitmap bigimg = Bitmap.createScaledBitmap(img, (int) (img.getWidth() * 1.5), (int) (img.getHeight() * 1.5), false);
            img = bigimg;
        }
        if (OP_MOVE.equals("move")) {
            x = 10;
            y = 10;
        }
        mCanvas.drawBitmap(img, x, y, mPaint);
    }

    int oldX = -1, oldY = -1;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mPaint.setStrokeWidth(OP_SIZE);
        mPaint.setColor(OP_PEN_COLOR);
        int X = (int) event.getX();
        int Y = (int) event.getY();
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            oldX = X;
            oldY = Y;
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            if (oldX != -1) {
                if (OP_STAMP != 1)
                    mCanvas.drawLine(oldX, oldY, X, Y, mPaint);
                invalidate();
                oldX = X;
                oldY = Y;
            }
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            if (oldX != -1) {
                Log.d("BEOM2", "oldX= " + oldX + "\n" + "X= " + X);
                if (OP_STAMP == 1) {
                    drawStamp(X, Y);
                } else {
                    mCanvas.drawLine(oldX, oldY, X, Y, mPaint);
                }
                invalidate();
            }
            oldX = -1;
            oldY = -1;
        }
        return true;
    }


    public void setStamp(int OP_STAMP) {
        this.OP_STAMP = OP_STAMP;
    }

    public void setBlur(int OP_BLUR) {
//        this.OP_BLUR = OP_BLUR;
        if (OP_BLUR == 1) {
            BlurMaskFilter blur = new BlurMaskFilter(100, BlurMaskFilter.Blur.NORMAL);
            mPaint.setMaskFilter(blur);
        } else if (OP_BLUR == 0) {
            mPaint.setMaskFilter(null);
        }
    }

    public void setColor(int OP_COLOR) {
//        this.OP_COLOR = OP_COLOR;
        if (OP_COLOR == 1) {
            Log.d("BEOM3", "필터적용중입니다.");
            float[] array = {
                    1f, 0f, 0f, 0f, -25f,
                    0f, 0f, 0f, 0f, -25f,
                    0f, 0f, 0f, 0f, -25f,
                    0f, 0f, 0f, 1f, 0f
            };
            ColorMatrix colorMatrix = new ColorMatrix(array);
            ColorMatrixColorFilter filter = new ColorMatrixColorFilter(colorMatrix);
            mPaint.setColorFilter(filter);
        } else if (OP_COLOR == 0) {
//            mPaint = new Paint();
            mPaint.setColorFilter(null);
            Log.d("BEOM3", "필터해제입니다.");
        }
    }

    public void setRotate(String OP_ROTATE) {
        int angle = 0;
        if (OP_ROTATE.equals("rotate")) {
            angle = 30;
        } else {
            angle -= 30;
        }
        mCanvas.rotate(angle, this.getWidth() / 2, this.getHeight() / 2);
    }

    public void setScale(String OP_SCALE) {
        this.OP_SCALE = OP_SCALE;
    }

    public void setMove(String OP_MOVE) {
        this.OP_MOVE = OP_MOVE;
    }

    public void setSkew(String OP_SKEW) {
//        this.OP_SKEW = OP_SKEW;
        float x = 0, y = 0;
        if (OP_SKEW.equals("skew")) {
            x = 0.2f;
        } else {
            x -= 0.2f;
        }
        mCanvas.skew(x, y);

    }

    public void setPenSize(int OP_SIZE) {
        this.OP_SIZE = OP_SIZE;
    }

    public void setPenColor(int OP_PEN_COLOR) {
        this.OP_PEN_COLOR = OP_PEN_COLOR;
    }

    public void clear() {
        mBitmap.eraseColor(Color.WHITE);
        invalidate();
    }

    public boolean Save(String file_name) {
        try {
            Toast.makeText(getContext(), "저장되었습니다!!", Toast.LENGTH_LONG).show();
            FileOutputStream out = new FileOutputStream(file_name);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);

            out.close();
            return true;
        } catch (FileNotFoundException e) {
            Log.e("FileNotFoundException", e.getMessage());
        } catch (IOException e) {
            Log.e("IOException", e.getMessage());
        }
        return false;
    }

    public void Open(String file_name) {
        try {
            String imgpath = file_name;
            Bitmap bm = BitmapFactory.decodeFile(imgpath);
            bm = Bitmap.createScaledBitmap(bm, bm.getWidth() / 2, bm.getHeight() / 2, false);
            mCanvas.drawBitmap(bm, mCanvas.getWidth() / 2 - bm.getWidth() / 2, mCanvas.getHeight() / 2 - bm.getHeight() / 2, mPaint);
            invalidate();
            Toast.makeText(getContext(), "load ok", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(getContext(), "파일이 없습니다", Toast.LENGTH_SHORT).show();
        }
    }
}
