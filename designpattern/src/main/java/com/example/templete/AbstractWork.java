package com.example.templete;

/**
 * Created by zeal on 2017/8/7.
 */

public abstract class AbstractWork {

    protected void getUp() {
        System.out.println("起床啦...");
    }

    protected abstract void goToWork();

    protected abstract void work();

    protected abstract void offwork();

    public final void newDay() {
        getUp();

        goToWork();

        work();
        offwork();
    }
}
