package com.example2;

/**
 * Created by zeal on 2017/7/2.
 */

public class ThreadA extends Thread {

    private HasSelfPrivateNum hasSelfPrivateNum ;
    public ThreadA(HasSelfPrivateNum hasSelfPrivateNum) {
        super();
        this.hasSelfPrivateNum = hasSelfPrivateNum;
    }

    @Override
    public void run() {
        super.run();
        hasSelfPrivateNum.addI("a");
    }
}
