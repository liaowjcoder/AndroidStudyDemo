package com.example.observer;

import java.util.ArrayList;
import java.util.List;
//被观察者
public class Subject {


    //观察者集合
    private List<Observer> mObservers = new ArrayList<Observer>();

    //添加一个观察者
    public void attach(Observer observer){
        System.out.println("attach an observer");
        mObservers.add(observer);
    }
    //移除一个观察者
    public void detach(Observer observer){
        System.out.println("detach an observer");
        mObservers.remove(observer);
    }

    //通知每一个观察者数据发生改变了
    public void notifyObservers(String state) {
        for (Observer observer : mObservers) {
            observer.update(state);
        }
    }


}