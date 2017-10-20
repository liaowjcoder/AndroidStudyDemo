package com.example;

/**
 * Created by zeal on 2017/9/25.
 */

public class Demo {

    private MyInterface myInterface;

    public void setListener(MyInterface myInterface) {
        this.myInterface = myInterface;
    }

    public void load() {


        new Thread(){
            @Override
            public void run() {
                super.run();

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                myInterface.onSuccess();
            }
        }.start();
    }

}
