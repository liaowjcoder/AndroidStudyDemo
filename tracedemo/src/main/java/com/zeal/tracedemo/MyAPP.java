package com.zeal.tracedemo;

import android.app.Application;
import android.os.Debug;
import android.util.Log;

/**
 * Created by zeal on 2017/9/6.
 */

public class MyAPP extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Debug.startMethodTracing("my_trace");
        Log.e("zeal","end");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
