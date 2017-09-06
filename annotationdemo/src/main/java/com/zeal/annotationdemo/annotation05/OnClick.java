package com.zeal.annotationdemo.annotation05;

import android.support.annotation.IdRes;
import android.view.View;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by zeal on 2017/9/3.
 */

@Retention(RetentionPolicy.RUNTIME)
@java.lang.annotation.Target(ElementType.METHOD)
@interface OnClick {
    @IdRes int[] value() default { View.NO_ID };
}
