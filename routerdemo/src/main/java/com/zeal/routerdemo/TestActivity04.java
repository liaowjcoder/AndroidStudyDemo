package com.zeal.routerdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;

/**
 * Created by zeal on 2017/8/5.
 */

@Route(path = "/test/activity04")
public class TestActivity04 extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_layout04);



        TextView textView = (TextView) findViewById(R.id.text);
        String data = getIntent().getStringExtra("extra");

        if (!TextUtils.isEmpty(data)) {
            textView.setText(data);
        }


    }
}
