package com.example.observer;

/**
 * Created by zeal on 2017/8/6.
 */

public class Test {
    public static void main(String[] args) {

        //创建具体的被观察者
        ConcreteSubject concreteSubject = new ConcreteSubject();

        //创建观察者
        Observer concreteObserver = new ConcreteObserver();
        Observer concreteObserver2 = new ConcreteObserver();
        //添加订阅关系
        concreteSubject.attach(concreteObserver);
        concreteSubject.attach(concreteObserver2);
        //发布被观察者状态发生改变
        concreteSubject.change("new state");

        concreteSubject.detach(concreteObserver);

        concreteSubject.change("new state2");

    }
}
