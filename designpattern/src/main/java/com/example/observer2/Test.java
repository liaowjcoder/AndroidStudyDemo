package com.example.observer2;

/**
 * Created by zeal on 2017/8/7.
 */

public class Test {//状态

    public static void main(String[] args) {

        TargetObservable observable = new TargetObservable();

        TargetObserver observer = new TargetObserver();
        TargetObserver observer2 = new TargetObserver();
        observable.addObserver(observer);
        observable.addObserver(observer2);


        observable.change("new state");

    }
}
