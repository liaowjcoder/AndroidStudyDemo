package com.example.observer2;

import java.util.Observable;
import java.util.Observer;
//实现至系统util包中的Observer
public class TargetObserver implements Observer{
    @Override
    public void update(Observable observable, Object o) {
        System.out.println("观察者收到数据的更新：" + o.toString());
    }
}
