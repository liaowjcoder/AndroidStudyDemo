package com.zeal.routerdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Route;

/**
 * Created by zeal on 2017/8/4.
 */
// 在支持路由的页面上添加注解(必选)
// 这里的路径需要注意的是至少需要有两级，/xx/xx
@Route(path = "/test/activity")
public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main2);


        Intent intent = getIntent();

        if (intent != null) {

            int age = intent.getIntExtra("age", 0);
            String name = intent.getStringExtra("name");

            Bundle bundle = intent.getBundleExtra("bundle");
            String bundleStr = bundle.getString("bundleStr");

            //info：age=25,name=zeal,bundleStr=i am bundle data
            Log.e("zeal", "info：" + "age=" + age + ",name=" + name
                    + ",bundleStr=" + bundleStr
            );

        }


    }
}
