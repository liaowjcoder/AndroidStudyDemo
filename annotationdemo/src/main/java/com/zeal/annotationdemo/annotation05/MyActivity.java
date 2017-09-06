package com.zeal.annotationdemo.annotation05;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.zeal.annotationdemo.R;


/**
 * Created by zeal on 2017/9/3.
 */

public class MyActivity extends AppCompatActivity {


    @BindView(R.id.textview)
    TextView text;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        MyInjector.inject(this);

        text.setText("hello world~~ ");

    }


    @OnClick({R.id.btn1,R.id.btn2})
    public void click(View view) {
        Toast.makeText(this, "view text:"+view.toString(), Toast.LENGTH_SHORT).show();
        Log.e("zeal","view text:"+view.toString());
    }
}
