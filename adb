启动一个activity：
    adb shell am start  -W org.fungo.fungolive/org.stagex.danmaku.activity.SplashActivity（带有启动时间）
        ThisTime  该activity启动耗时
        TotalTime  应用自身启动耗时=ThisTime+应用application等资源启动时间
        WaitTime  系统启动应用耗时=TotalTime+系统资源启动时间


        该命令具体实现在/frameworks/base/cmds/am/src/com/android/commands/am/Am.java，原理是跨Binder调用ActivityManagerService.startActivityAndWait() 接口，其中返回数据分别调用对应
        startTime：  调用startActivityAndWait()的时间点
        endTime：   调用startActivityAndWait()函数调用返回的时间点
        WaitTime：  调用startActivityAndWait()调用耗时。
        再通过之间的计算得到。


    am start  -n org.fungo.fungolive/org.stagex.danmaku.activity.SplashActivity




    android.intent.category.LAUNCHER