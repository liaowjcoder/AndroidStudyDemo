package com.zeal.annotationandreflect.annotation02;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by liaowj on 2017/7/17.
 */

@Retention(RetentionPolicy.RUNTIME)
public @interface AnnotationTest {



    String value();
//    String name() ;
}
