package com.zeal.routerdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Route;

/**
 * Created by zeal on 2017/8/5.
 */

@Route(path = "/test/activity01")
public class TestActivity01 extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_layout01);


        Intent intent = getIntent();

        String name = intent.getStringExtra("name");

        Log.e("zeal","TestActivity get name :"+name);


        setResult(999);
    }
}
