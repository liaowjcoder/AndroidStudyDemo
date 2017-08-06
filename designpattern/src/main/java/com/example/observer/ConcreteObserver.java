package com.example.observer;

/**
 * Created by zeal on 2017/8/6.
 */

public class ConcreteObserver implements Observer {

    //观察者状态
    private String observerState;

    @Override
    public void update(String state) {
        //改变观察者状态让其跟目标一致
        this.observerState = state;
        System.out.println("concrete observer "+state+" change...");
    }
}
