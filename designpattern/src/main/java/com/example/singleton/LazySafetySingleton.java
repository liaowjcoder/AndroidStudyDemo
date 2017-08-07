package com.example.singleton;

/**
 * Created by zeal on 2017/8/7.
 */

/*
懒汉式线程安全,缺点就是性能消耗
 */
public class LazySafetySingleton {

    private static LazySafetySingleton sInstance;


    private LazySafetySingleton() {


    }

    //同步方法，让每一次只有一个线程能够进程该方法
    public static synchronized LazySafetySingleton getInstance() {
        if (sInstance == null) {
            sInstance = new LazySafetySingleton();
        }
        return sInstance;
    }


    //同步代码块，让每一次只有一个线程能够进程该方法
    public static synchronized LazySafetySingleton getInstance1() {
        synchronized (LazySafetySingleton.class) {
            if (sInstance == null) {
                sInstance = new LazySafetySingleton();
            }
        }
        return sInstance;
    }

    public static void createString() {
        System.out.println("create string...");
    }
}
