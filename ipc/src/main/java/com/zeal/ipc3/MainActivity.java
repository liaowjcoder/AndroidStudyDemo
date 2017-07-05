package com.zeal.ipc3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.zeal.ipc.R;

/*
当开启多个进程时，那么 MyApplication 会创建多次，通过在 MyApplication
 oncreate 中打印 log 可以知道印证这个结论。


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
