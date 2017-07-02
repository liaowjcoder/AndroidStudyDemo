package com.example2;

public class ThreadDemo {

    /*
    多个线程操作同一个对象的成员变量就会出现线程安全问题。
    a set over
    b set over
    b num:200
    a num:200（有问题）


    使用 synchronized 关键字加锁解决线程安全问题
    a set over
    a num:100（2s之后才开始执行这段代码）
    b set over
    b num:200
    结论：在两个线程访问同一个对象的同步方法时一定是线程安全的。
     */
    public static void main(String[] args) {

        HasSelfPrivateNum hasSelfPrivateNum = new HasSelfPrivateNum();

        ThreadA threadA = new ThreadA(hasSelfPrivateNum);

        ThreadB threadB = new ThreadB(hasSelfPrivateNum);

        threadA.start();
        threadB.start();
    }

}
