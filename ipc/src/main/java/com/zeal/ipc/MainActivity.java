package com.zeal.ipc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/*
一个应用开启多个进程，当某一个进程挂了，不会影响其他进程。
  com.zeal.ipc:remote:表示是该应用的私有进程，其他应用的组件是不可以与其跑在同一进程中的
  com.zeal.ipc.remote：进程名称就是这个，这种叫做全局进程，其他应用通过 SHAREID 可以与其跑在同一个进程中
  (签名要一致)，可以共享一些私有数据（例如 data 目录）
 */
public class MainActivity extends AppCompatActivity {
    private TextView textview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textview= (TextView) findViewById(R.id.textview);

        textview.setText("MainActivity");
    }

    public void onclick(View view) {

        startActivity(new Intent(this,Main2Activity.class));

    }
}
