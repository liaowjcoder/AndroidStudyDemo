package com.zeal.asynctaskdemo.asynctask01;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executor;

/**
 * Created by zeal on 2017/7/23.
 */

public class Task implements Runnable {
    private Executor executor;
    private String name;

    public Task(String name, Executor executor) {
        this.name = name;
        this.executor = executor;
    }

    @Override
    public void run() {
        try {

            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

            String date = sdf.format(new Date());

            Log.e("zeal","before "+ name +"-"+ date + " running " + executor.toString());
            Thread.sleep(2000);
            Log.e("zeal","after"+ name +"-"+ date + " running " + executor.toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
