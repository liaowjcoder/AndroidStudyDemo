package com.zeal.annotationdemo.annotation04;

import android.support.annotation.CallSuper;

/**
 * Created by zeal on 2017/9/3.
 */

public abstract class Person {
    @CallSuper
    public void onCreate() {

        init();

        initBody();

        initLanguage();

        initWorking();
    }

    private void initWorking() {
    }

    private void initLanguage() {

    }

    private void initBody() {
    }

    private void init() {
    }


}
