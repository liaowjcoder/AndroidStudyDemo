package com.example3;

/**
 * Created by zeal on 2017/7/2.
 */

public class HasSelfPrivateNum {

    //num作为成员变量
    private int num = 0;

    /*

     */
    synchronized public void addI(String username) {
        try {
            if ("a".equals(username)) {
                num = 100;
                System.out.println("a set over");
                Thread.sleep(2000);
            } else {
                num = 200;
                System.out.println("b set over");
            }
            System.out.println(username + " num:" + num);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
