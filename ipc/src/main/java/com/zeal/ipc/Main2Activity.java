package com.zeal.ipc;

import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    private TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textview = (TextView) findViewById(R.id.textview);

        textview.setText("Main2Activity");


        findViewById(R.id.kill).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //杀死自己
                Process.killProcess(Process.myPid());
                //System.exit(0);
            }
        });
    }
    public void onclick(View view) {

        startActivity(new Intent(this,Main3Activity.class));

    }
}
