package com.zeal.webviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
/*
1.native调用h5中的js方法：mWebView.loadUrl("javascript:changeColor('"+color+"');");
mWebView.loadUrl("javascript:methodName();");

2.h5调用netive方法
定义JSInterface类，并定义需要被h5调用的方法
调用addJavascriptInterface并为该接口定义一个别名callback
mWebView.addJavascriptInterface(new JSInterface(),"callback");
在h5中利用callback调用该方法：<a onclick="callback.callNativeMethod(11,10,'cc',true)">h5调用客户端方法</a>
 */
public class MainActivity extends AppCompatActivity {
    private WebView mWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWebView = (WebView) findViewById(R.id.webview);


        WebSettings settings = mWebView.getSettings();

        //开启js
        settings.setJavaScriptEnabled(true);

        //加载本地资源
        mWebView.loadUrl("file:///android_asset/test.html");


        findViewById(R.id.changecolor).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //调用h5中的js方法改变颜色
                String color ="#00ee00";
                mWebView.loadUrl("javascript:changeColor('"+color+"');");
            }
        });


        //h5 call native method
        //注册接口
        mWebView.addJavascriptInterface(new JSInterface(this),"callback");
    }





}
