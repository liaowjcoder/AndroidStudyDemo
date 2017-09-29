package com.zeal.memoryoptimization4_ui_time_printer;

import android.app.Application;

/**
 * Created by zeal on 2017/9/29.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        BlockDetectedByPrinter.start();
    }
}
