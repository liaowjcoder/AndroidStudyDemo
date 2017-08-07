package com.example.singleton;

/**
 * Created by zeal on 2017/8/7.
 */
/*
静态内部类实现单例模式
static
final
实现的jvm同步控制,保证的线程安全，没有性能缺陷
 */

public class StaticInnerSingleton {

    private StaticInnerSingleton(){}
    public static StaticInnerSingleton getInstance() {
        return SingleHolder.INSTANCE;
    }
    public static class SingleHolder{
        private static final StaticInnerSingleton INSTANCE = new StaticInnerSingleton();
    }

}
