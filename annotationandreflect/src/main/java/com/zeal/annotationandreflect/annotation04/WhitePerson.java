package com.zeal.annotationandreflect.annotation04;

/**
 * Created by zeal on 2017/9/3.
 */

public class WhitePerson extends Person {

    @Override
    public void onCreate() {
        //必须先调用父类的onCeate方法，先把父类的逻辑走完，然后接着编写自己的代码
        super.onCreate();

        //do something
    }
}
