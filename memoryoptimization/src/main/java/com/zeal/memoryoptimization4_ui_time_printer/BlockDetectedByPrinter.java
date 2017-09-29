package com.zeal.memoryoptimization4_ui_time_printer;

import android.os.Looper;
import android.util.Printer;

/**
 * Created by zeal on 2017/9/29.
 */

public class BlockDetectedByPrinter {

    public static void start() {


        Looper.getMainLooper().setMessageLogging(new Printer() {

            private static final String START = ">>>>> Dispatching";
            private static final String END = "<<<<< Finished";

            @Override
            public void println(String x) {
                //在阀值时间内去执行打印堆栈log的信息
                if (x.startsWith(START)) {
                    LogMonitor.getInstance().statrMonitor();
                }
                //如果在阀值时间内，该message执行完毕，那么该则不会打印堆栈log，直接把该消息移除
                //如果是超过阀值时间执行END操作，那么就会执行打堆栈log的信息
                if (x.startsWith(END)) {
                    LogMonitor.getInstance().removeMonitor();
                }

            }
        });

    }
}
