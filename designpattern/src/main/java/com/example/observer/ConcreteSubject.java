package com.example.observer;

/**
 * Created by zeal on 2017/8/6.
 */

public class ConcreteSubject extends Subject {

    //状态
    private String state;

    public String getState() {
        return state;
    }

    //状态发生改变，通知观察者
    public void change(String state){
        this.state = state;
        notifyObservers(state);
    }
}
