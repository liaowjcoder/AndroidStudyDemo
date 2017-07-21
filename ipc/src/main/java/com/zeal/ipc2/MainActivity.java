package com.zeal.ipc2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.zeal.ipc.R;

/*
在进程间观察 UserManager 中的 sUserId 的值是否能共享

每一个进程都一个虚拟机，每一个虚拟机都会有不同的内存空间，因此在读取 UserManager
这个对象时就产生不同的副本。


 */
public class MainActivity extends AppCompatActivity {
    private TextView textview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textview= (TextView) findViewById(R.id.textview);
        //在主进程中修改sUserId的值
        UserManager.sUserId = 2;
        textview.setText("MainActivity:"+UserManager.sUserId);

    }

    public void onclick(View view) {

        startActivity(new Intent(this,Main2Activity.class));

    }
}
