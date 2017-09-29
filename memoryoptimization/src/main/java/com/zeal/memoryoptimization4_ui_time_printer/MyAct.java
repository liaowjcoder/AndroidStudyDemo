package com.zeal.memoryoptimization4_ui_time_printer;

import android.app.Activity;
import android.os.Bundle;

import com.zeal.memoryoptimization.R;

/**
 * Created by zeal on 2017/9/29.
 */

public class MyAct extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
