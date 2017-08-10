package com.zeal.webviewdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebView;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    private WebView mWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        TextView text = (TextView) findViewById(R.id.text);

        Intent intent = getIntent();

        int movieId = intent.getIntExtra("movieId", -1);

        String email = intent.getStringExtra("email");

        Log.e("zeal","movieId="+movieId+"--"+"email="+email);

        text.setText("movieId="+movieId+"--"+"email="+email);
    }





}
