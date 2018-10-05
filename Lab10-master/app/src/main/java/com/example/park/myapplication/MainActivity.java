package com.example.park.myapplication;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ArrayList<URL> Data = new ArrayList<URL>();
    ArrayList<String> arrayList = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    WebView webView;
    EditText e1;
    ProgressDialog dialog;
    Animation animation;
    LinearLayout linear1, linear2;
    ListView l1;
    String MAIN_URL = "https://www.hanyang.ac.kr";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        ListViewMethod();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 1, 0, "즐겨찾기추가");
        menu.add(0, 2, 0, "즐겨찾기목록");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == 1) {
            setView(0);
            webView.loadUrl("file:///android_asset/www/urladd.html");
            linear2.setAnimation(animation);
            animation.start();
        } else if (item.getItemId() == 2) {
            setView(1);
        }
        return super.onOptionsItemSelected(item);
    }

    Handler handler = new Handler(); // ui thread 를 쓰기때문에
    class JavaScriptMethods {
        @JavascriptInterface
        public void AddUrl(String name, String url) {
            if (!CheckUrl(url)) {
                Toast.makeText(getApplicationContext(), name + "이 즐겨찾기에 추가되었습니다.", Toast.LENGTH_LONG).show();
                Data.add(new URL(name, url));
                arrayList.add(name);
                adapter.notifyDataSetChanged();
            } else {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        webView.loadUrl("javascript:displayMsg()");
                    }
                });
            }
        }

        @JavascriptInterface
        public void Show() {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    linear2.setVisibility(View.VISIBLE);
                }
            });
        }
    }

    void init() {
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
        l1 = (ListView) findViewById(R.id.listview1);
        l1.setAdapter(adapter);
        linear1 = (LinearLayout) findViewById(R.id.linear1);
        linear2 = (LinearLayout) findViewById(R.id.urllinear);
        e1 = (EditText) findViewById(R.id.edt1);
        webView = (WebView) findViewById(R.id.webview);
        webView.addJavascriptInterface(new JavaScriptMethods(), "MyApp");
        webView.loadUrl(MAIN_URL);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                dialog.setMessage("Loading...");
                dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                dialog.show();
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {// 이게 안디어있으면 밖에서 찾는다.
                return super.shouldOverrideUrlLoading(view, request);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                e1.setText(url);
            }
        });
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress >= 100) dialog.dismiss();
            }

            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                result.confirm();
                return super.onJsAlert(view, url, message, result);
            }
        });
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setSupportZoom(true);
        webSettings.setCacheMode(webSettings.LOAD_NO_CACHE);
        dialog = new ProgressDialog(this);
        animation = AnimationUtils.loadAnimation(this, R.anim.translate_top);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                linear2.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public void onClick(View v) {
        if (v.getId() == R.id.gobtn) {
            String GoUrl = e1.getText().toString();
            webView.loadUrl(GoUrl);
        }
    }

    void setView(int p) {
        if (p == 0) {
            linear1.setVisibility(View.VISIBLE);
            l1.setVisibility(View.INVISIBLE);
        } else {
            linear1.setVisibility(View.INVISIBLE);
            l1.setVisibility(View.VISIBLE);
        }
    }

    boolean CheckUrl(String url) {
        for (int i = 0; i < Data.size(); i++) {
            if (url.equals(Data.get(i).getUrl())) {
                return true;
            }
        }
        return false;
    }

    void ListViewMethod() {
        l1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String url = Data.get(i).getUrl();
                webView.loadUrl(url);
                setView(0);
                linear2.setVisibility(View.VISIBLE);
            }
        });
        l1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final int position = i;
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("삭제")
                        .setMessage("삭제하시겠습니까?")
                        .setNegativeButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Data.remove(position);
                                arrayList.remove(position);
                                adapter.notifyDataSetChanged();
                            }
                        })
                        .setPositiveButton("취소", null)
                        .show();
                return true;
            }
        });
    }
}