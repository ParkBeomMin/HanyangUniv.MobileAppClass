package com.example.park.myapplication;

import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.jar.Manifest;

public class MainActivity extends AppCompatActivity {
    EditText e1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkPermission();
        e1 = (EditText)findViewById(R.id.et1);
    }

    public void MyOnClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                try {
                    BufferedWriter bw = new BufferedWriter(new FileWriter(getFilesDir() +
                            "test.txt", true)); // true : 어펜딩, 붙어서 계속 저장 / false : 새로 만듦
                    bw.write("안녕하세요 Hello");
                    bw.close();
                    Toast.makeText(this, "저장완료", Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btn2:
                try {
                    BufferedReader br = new BufferedReader(new FileReader(getFilesDir() + "test.txt"));
                    String readStr = "";
                    String str = null;
                    while ((str = br.readLine()) != null)
                        readStr += str + "\n"; // readLine -> 엔터나올때까지
                    br.close();
                    Toast.makeText(this, readStr.substring(0, readStr.length() - 1), Toast.LENGTH_SHORT).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "File not found", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn3:
                try {
                    InputStream is = getResources().openRawResource(R.raw.about);
                    byte[] readStr = new byte[is.available()]; // 파일크기
                    is.read(readStr);
                    is.close();
                    Toast.makeText(this, new String(readStr), Toast.LENGTH_SHORT).show(); // 바이트배열에 있기때문에 문자열로 변환
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn4 :
                try {
                    BufferedWriter bw = new BufferedWriter(new FileWriter(getExternalPath()+"mydiary/" +
                            "test1.txt", true)); // true : 어펜딩, 붙어서 계속 저장 / false : 새로 만듦
                    bw.write("안녕하세요 외부!");
                    bw.close();
                    Toast.makeText(this, "저장완료", Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btn5 :
                try {
                    BufferedReader br = new BufferedReader(new FileReader(getExternalPath() +"mydiary/"+ "test1.txt"));
                    String readStr = "";
                    String str = null;
                    while ((str = br.readLine()) != null)
                        readStr += str + "\n"; // readLine -> 엔터나올때까지
                    br.close();
                    Toast.makeText(this, readStr.substring(0, readStr.length() - 1), Toast.LENGTH_SHORT).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "File not found", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn6 :
                String path = getExternalPath();
                File file = new File(path + "mydiary");
                file.mkdir();

                String msg = "디렉터리생성";
                if(file.isDirectory() == false)
                    msg="디렉터리 생성오류";
                    Toast.makeText(this,msg, Toast.LENGTH_LONG).show();

                break;
            case R.id.btn7 :
                String path1 = getExternalPath();
                File[] files = new File(path1 + "mydiary").listFiles();
                String str = "";
                for(File f:files)
                    str += f.getName() + "\n";
                e1.setText(str);
                break;
        }
    }

    void checkPermission() {
        int permissioninfo = ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if(permissioninfo == PackageManager.PERMISSION_GRANTED){
            Toast.makeText(getApplicationContext(), "SDCard 쓰기 권한 있음",Toast.LENGTH_SHORT).show();
        }
        else {
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                Toast.makeText(getApplicationContext(), "권한의 필요성 설명",Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
            }
            else {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 100) {
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "SD카드 쓰기권한 승인", Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(this, "SD카드쓰기권한거부",Toast.LENGTH_LONG).show();
            }
        }
    }

    public String getExternalPath() {
        String sdPath="";
        String ext = Environment.getExternalStorageState();
        if(ext.equals(Environment.MEDIA_MOUNTED)) {
            sdPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/";
            Log.d("PATH",sdPath);
        }
        else {
            sdPath = getFilesDir() + "";
            Toast.makeText(getApplicationContext(), sdPath, Toast.LENGTH_LONG).show();
        }
        return sdPath;
    }
}
