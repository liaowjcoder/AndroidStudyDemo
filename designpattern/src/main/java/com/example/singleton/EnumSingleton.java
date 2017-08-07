package com.example.singleton;

/**
 * Created by zeal on 2017/8/7.
 */

//枚举单例
    //枚举的特点：
    //写法简单，线程安全
public enum EnumSingleton {

    //定义一个枚举类型的实例
    INSTANCE;

    public void doSome() {

        //do something...
    }


}
