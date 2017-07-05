package com.zeal.ipc3;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Process;
import android.util.Log;

import java.util.List;

/**
 * Created by liaowj on 2017/7/5.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("zeal", "processName:" + getProcssName());
    }

    /**
     * 获取当前进程名称
     * @return
     */
    public String getProcssName() {

        ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);

        //得到正在运行的进程
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = am.getRunningAppProcesses();

        for (ActivityManager.RunningAppProcessInfo runningAppProcess : runningAppProcesses) {
            if (runningAppProcess.pid == Process.myPid()) {
                return runningAppProcess.processName;
            }
        }
        return null;
    }
}
