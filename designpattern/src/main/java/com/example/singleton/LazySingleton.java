package com.example.singleton;

/**
 * Created by zeal on 2017/8/7.
 */

/*
懒汉式:多线程并发条件的单例是失效的。
 */
public class LazySingleton {

    private static LazySingleton sInstance;


    private LazySingleton() {


    }
    public static LazySingleton getInstance() {
        if(sInstance==null) {
            sInstance = new LazySingleton();
        }
        return sInstance;
    }
    
    public static void createString() {
        System.out.println("create string...");
    }
}
