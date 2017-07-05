package com.zeal.ipc2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.zeal.ipc.R;

public class Main2Activity extends AppCompatActivity {
    private TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textview = (TextView) findViewById(R.id.textview);
        //在 MainActivity 中将该值已经改为2了但是这里却还是1
        textview.setText("Main2Activity："+UserManager.sUserId);
    }
    public void onclick(View view) {

        startActivity(new Intent(this,Main3Activity.class));

    }
}
