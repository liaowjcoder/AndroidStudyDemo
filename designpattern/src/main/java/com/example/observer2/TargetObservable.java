package com.example.observer2;

import java.util.Observable;

/**
 * Created by zeal on 2017/8/7.
 */
//继承至系统自带的Observable
public class TargetObservable extends Observable {


    //状态
    private String state;

    public String getState() {
        return state;
    }

    //状态发生改变，通知观察者
    public void change(String state){
        this.state = state;
        setChanged();
        //改变的参数，它是Observer中update方法的第二个参数
        //如果这里不传参数，那么在Observer中update方法获取到的第二个参数则为null
        notifyObservers(state);
    }

}
