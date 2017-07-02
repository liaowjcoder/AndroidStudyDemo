package com.example2;

/**
 * Created by zeal on 2017/7/2.
 */

public class HasSelfPrivateNum {

    //num作为成员变量
    private int num = 0;
    //有线程安全问题的方法
//    public void addI(String username) {
//        try{
//            if("a".equals(username)) {
//                num = 100;
//                System.out.println("a set over");
//                Thread.sleep(2000);//当线程1走到这里，sleep了，释放了执行权，这时线程2开始执行
//            }else{
//                num = 200;
//                System.out.println("b set over");
//            }
//            System.out.println(username+" num:"+num);//线程2因为没有 sleep 所以执行的很快，将 num = 200 ，因此当
//            //线程1 2秒之后醒来，发现 num 已经被修改了，所以线程1打印的 num 就是 200，而不是100
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }

    /*
    使用 synchronized 关键字加锁解决线程安全问题

     */
    synchronized public void addI(String username) {
        try {
            if ("a".equals(username)) {
                num = 100;
                System.out.println("a set over");
                Thread.sleep(2000);//当线程1走到这里，sleep了，释放了执行权，这时线程2开始执行
            } else {
                num = 200;
                System.out.println("b set over");
            }
            System.out.println(username + " num:" + num);//线程2因为没有 sleep 所以执行的很快，将 num = 200 ，因此当
            //线程1 2秒之后醒来，发现 num 已经被修改了，所以线程1打印的 num 就是 200，而不是100
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
