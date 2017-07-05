package com.zeal.ipc3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.zeal.ipc.R;

public class Main3Activity extends AppCompatActivity {

    private TextView textview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textview= (TextView) findViewById(R.id.textview);

        textview.setText("Main3Activity");
    }

    public void onClick(View view) {

    }
}
