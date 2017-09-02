package com.zeal.annotationandreflect.annotation02;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by liaowj on 2017/7/17.
 */

@Retention(RetentionPolicy.RUNTIME)
public @interface AnnotationTest {



    //这个value方法在被使用不需要指定value名字
    String value();
//    String name() ;
}
