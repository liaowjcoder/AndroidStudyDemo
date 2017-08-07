package com.example.singleton;

/**
 * Created by zeal on 2017/8/7.
 */
/*
DCL模式下的懒汉式
特点:看似解决了性能问题，也解决了单例问题，但是getInstance内部在创建该实例是有严重问题的
1.JVM 给 DCLLazySingleton 分配内存
2.调用DCLLazySingleton构造创建该对象
3.将sInstance指向该对象
但是jvm的即时编译器存在指令重排序的优化，也就是以上说的3步不会按照想要的顺序执行，这就导致线程不安全。
解决方案：使用volatile标识，它能保证线程不会存有sInstance的副本，而是每次都去内存中读取，
禁止jvm指令重排序优化，不存在重排序情况。

 */
public class DclLazySingleton {

    //private static DclLazySingleton sInstance;

    private volatile static DclLazySingleton sInstance;

    private DclLazySingleton() {

    }

    public static DclLazySingleton getIntance() {
        if (sInstance == null) {
            synchronized (DclLazySingleton.class) {
                if (sInstance == null) {
                    sInstance = new DclLazySingleton();
                }
            }
        }
        return sInstance;
    }

}
