package com.zeal.annotationandreflect.annotation01;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by liaowj on 2017/7/17.
 * 定义一个注解，注解的信息会在运行时保留
 */

@Retention(RetentionPolicy.RUNTIME)
public @interface Target {
}
