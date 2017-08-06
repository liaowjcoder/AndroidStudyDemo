package com.example.observer;

/**
 * Created by zeal on 2017/8/6.
 */
//观察者
public interface Observer {
    //参数state就是需要改变的东西
    void update(String state);
}
