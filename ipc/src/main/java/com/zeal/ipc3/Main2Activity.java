package com.zeal.ipc3;

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
        textview.setText("Main2Activity");
    }
    public void onclick(View view) {

        startActivity(new Intent(this,Main3Activity.class));

    }
}
