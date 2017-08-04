package com.zeal.routerdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //跳转到第SecondActivity
        findViewById(R.id.click2SecondAct).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //跳转操作-不带参数的
                //startActivity(new Intent(MainActivity.this,SecondActivity.class));
                ARouter.getInstance().build("/test/activity").navigation();
            }
        });
    }
}
