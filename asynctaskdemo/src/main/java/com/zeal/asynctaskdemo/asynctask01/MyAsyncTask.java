package com.zeal.asynctaskdemo.asynctask01;

import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by zeal on 2017/7/23.
 */

public class MyAsyncTask extends AsyncTask<String, Void, String> {


    @Override
    protected String doInBackground(String[] params) {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return params[0];
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Log.e("zeal", s + "#onPostExecute");
    }
}
