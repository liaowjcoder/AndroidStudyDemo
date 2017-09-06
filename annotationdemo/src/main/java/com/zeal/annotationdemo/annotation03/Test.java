package com.zeal.annotationdemo.annotation03;

/**
 * Created by liaowj on 2017/7/17.
 */

public class Test {
    //定义在属性的注解
    @AnnotationFile
    private String message;
    public static void main(String[] args) {



    }

    //当将该注解用在方法上就会出错
//    @AnnotationFile()
//    public void method() {
//
//    }



}
