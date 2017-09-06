package com.zeal.annotationdemo.annotation02;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by liaowj on 2017/7/17.
 * 给注解定义属性
 */

@Retention(RetentionPolicy.RUNTIME)
public @interface AnnotationFile {

    //方法返回值默认是 hello java
    String getMessage() default "hello java";

    String getNickName();

    //StringBuilder get();

    int[] getArray() default {1,2,3,4};

    //Annotation
    //当 AnnotationTest 有一个属性叫做 value; 那么就不用写属性名称
    AnnotationTest annotation() default @AnnotationTest("AnnotationTest");

    //枚举类型

}
