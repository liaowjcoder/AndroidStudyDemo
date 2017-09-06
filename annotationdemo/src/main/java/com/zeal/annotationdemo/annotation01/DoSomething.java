package com.zeal.annotationdemo.annotation01;

/**
 * Created by liaowj on 2017/7/17.
 */

public class DoSomething {

    @Target
    public void doSomething() {
        System.out.print("do something");
    }
}
