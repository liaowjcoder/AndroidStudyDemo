package com.example3;

/**
 * Created by zeal on 2017/7/2.
 */

public class ThreadB extends Thread {

    private HasSelfPrivateNum hasSelfPrivateNum ;
    public ThreadB(HasSelfPrivateNum hasSelfPrivateNum) {
        super();
        this.hasSelfPrivateNum = hasSelfPrivateNum;
    }

    @Override
    public void run() {
        super.run();
        hasSelfPrivateNum.addI("b");
    }
}
