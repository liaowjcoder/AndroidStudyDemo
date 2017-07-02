package com.example3;

public class ThreadDemo {

    /*
    多个线程操作多个对象的成员变量不会出现线程安全问题。
    a set over
    b set over
    b num:200
    a num:100

    因为访问的是同一个类的两个不同对象hasSelfPrivateNum和hasSelfPrivateNum2，
    因此 JVM 在同步方法中会创建两把锁,这种方式不存在线程安全问题，因为它不是操作同一个对象的成员变量。
    （当前addI 方法所使用的锁是当前类对象）
     */
    public static void main(String[] args) {

        HasSelfPrivateNum hasSelfPrivateNum = new HasSelfPrivateNum();
        HasSelfPrivateNum hasSelfPrivateNum2 = new HasSelfPrivateNum();

        ThreadA threadA = new ThreadA(hasSelfPrivateNum);

        ThreadB threadB = new ThreadB(hasSelfPrivateNum2);

        threadA.start();
        threadB.start();
    }

}
