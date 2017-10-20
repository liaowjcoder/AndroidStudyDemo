package com.example;

public class MyClass {


    public static void main(String[] args) {
        Demo demo = new Demo();
        MyInterface onSuccess = new MyInterface() {
            @Override
            public void onSuccess() {
                System.out.println("onSuccess");
            }
        };
        demo.setListener(onSuccess);

        demo.load();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onSuccess = null;

    }

}


