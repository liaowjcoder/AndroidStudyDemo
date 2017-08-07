package com.example.singleton;

/**
 * Created by zeal on 2017/8/7.
 */
/*
验证懒汉式多线程并发条件的单例是失效的。
1656966850
1776951462
1776951462
1776951462
1797317857
1776951462
1776951462
1776951462
1776951462
1776951462
 */
public class LazyTest extends Thread {

    public static void main(String[] args) {

        LazyTest[] lazys = new LazyTest[10];
        for (int i = 0; i<lazys.length;i++) {
            lazys[i] = new LazyTest();
        }
        for (LazyTest lazy : lazys) {
            lazy.start();
        }
    }

    @Override
    public void run() {
        super.run();
        //System.out.println(LazySingleton.getInstance().hashCode());
        System.out.println(LazySafetySingleton.getInstance1().hashCode());
    }
}
