package com.example;

public class ThreadDemo {

    /*
    操作同一个对象的成员方法中的局部变量 num ，这种方式是线程安全的。
    因为每次调用 addI 方法都会创建一个 num 变量，多个线程调用就会调用多次，每一个线程调用该方法
    都会有一个 num 变量，所以不存在线程安全问题。
    a set over
    b set over
    a num:100
    b num:200
     */
    public static void main(String[] args) {

        HasSelfPrivateNum hasSelfPrivateNum = new HasSelfPrivateNum();

        ThreadA threadA = new ThreadA(hasSelfPrivateNum);

        ThreadB threadB = new ThreadB(hasSelfPrivateNum);

        threadA.start();
        threadB.start();
    }

}
