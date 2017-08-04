package com.zeal.routerdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;

/**
 * Created by zeal on 2017/8/5.
 */

@Route(path = "/test/activity02")
public class TestActivity02 extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_layout02);

        String value = getIntent().getStringExtra("key");

        Toast.makeText(this, "value:"+value, Toast.LENGTH_SHORT).show();
    }
}
