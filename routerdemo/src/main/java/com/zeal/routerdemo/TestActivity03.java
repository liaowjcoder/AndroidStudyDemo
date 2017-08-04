package com.zeal.routerdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

/**
 * Created by zeal on 2017/8/5.
 */

@Route(path = "/test/activity03")
public class TestActivity03 extends AppCompatActivity {

    @Autowired
    String name;

    //需要使用Autowired注解就不可以使用private修饰，但是可以使用static
    @Autowired
    int age;
//    static int age;
//    private int age;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_layout03);


        ARouter.getInstance().inject(this);

        TextView textView = (TextView) findViewById(R.id.text);


        textView.setText("name=" + name + "\n" + "age=" + age);


    }
}
