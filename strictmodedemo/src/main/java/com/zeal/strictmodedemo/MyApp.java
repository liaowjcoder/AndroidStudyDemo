package com.zeal.strictmodedemo;

import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.os.Handler;
import android.os.StrictMode;

import java.util.ArrayList;

/**
 * Created by zeal on 2017/10/24.
 * <p>
 * 在Application#onCreate直接调用strictMode的配置是不起作用的
 * <p>
 * 有人反馈：
 * https://stackoverflow.com/questions/23997448/why-setting-up-strictmode-not-working-in-application-without-handler
 * google的issue tracker'
 * https://issuetracker.google.com/issues/36951662
 * <p>
 * 严格模式主要检测两大问题，一个是线程策略，即TreadPolicy，另一个是VM策略，即VmPolicy。
 * <p>
 * ThreadPolicy
 * <p>
 * 线程策略检测的内容有
 * <p>
 * 自定义的耗时调用 使用detectCustomSlowCalls()开启
 * 磁盘读取操作 使用detectDiskReads()开启
 * 磁盘写入操作 使用detectDiskWrites()开启
 * 网络操作 使用detectNetwork()开启
 * <p>
 * VmPolicy
 * <p>
 * 虚拟机策略检测的内容有
 * <p>
 * Activity泄露 使用detectActivityLeaks()开启
 * 未关闭的Closable对象泄露 使用detectLeakedClosableObjects()开启
 * 泄露的Sqlite对象 使用detectLeakedSqlLiteObjects()开启
 * 检测实例数量 使用setClassInstanceLimit()开启
 */

public class MyApp extends Application {
    public static ArrayList<Activity> sLeakyActivities = new ArrayList<Activity>();


    @Override
    public void onCreate() {
        if (BuildConfig.DEBUG) {
            enableStrictMode();
        }
        super.onCreate();
    }

    private void enableStrictMode() {
        if (Build.VERSION.SDK_INT >= 9) {
            turnOnStrictMode();
        }

        if (Build.VERSION.SDK_INT >= 16) {
            //restore strict mode after onCreate() returns.
            new Handler().postAtFrontOfQueue(new Runnable() {
                @Override
                public void run() {
                    turnOnStrictMode();
                }
            });
        }
    }

    /**
     * detectAll
     */
    private void turnOnStrictMode() {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().
                //setClassInstanceLimit(MainActivity.class,1)
                penaltyLog().build());
    }

}
