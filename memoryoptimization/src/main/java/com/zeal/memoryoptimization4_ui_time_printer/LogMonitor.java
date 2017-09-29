package com.zeal.memoryoptimization4_ui_time_printer;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.Log;

/**
 * Created by zeal on 2017/9/29.
 */

public class LogMonitor {

    private static LogMonitor INSTANCE = new LogMonitor();
    private HandlerThread mLogThread = new HandlerThread("log");
    private Handler mIOHandler;


    private LogMonitor() {

        mLogThread.start();

        mIOHandler = new Handler(mLogThread.getLooper());
    }

    public static LogMonitor getInstance() {

        return INSTANCE;

    }

    private static Runnable mLogRunnable = new Runnable() {
        @Override
        public void run() {
            StringBuilder sb = new StringBuilder();
            StackTraceElement[] stackTraceElements = Looper.getMainLooper().getThread().getStackTrace();
            for (int i = 0; i < stackTraceElements.length; i++) {
                sb.append(stackTraceElements[i].toString()+"\n");
            }
            Log.e("zeal",sb.toString());
        }
    };


//    public boolean isMonitor() {
//        return mIOHandler
//    }

    public void statrMonitor() {
        mIOHandler.postDelayed(mLogRunnable,1000);
    }


    public void removeMonitor() {
        mIOHandler.removeCallbacks(mLogRunnable);
    }
}

