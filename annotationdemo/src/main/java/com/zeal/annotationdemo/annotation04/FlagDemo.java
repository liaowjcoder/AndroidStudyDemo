package com.zeal.annotationdemo.annotation04;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by liaowj on 2017/7/17.
 */

public class FlagDemo {

    public final static String RED = "red";
    public final static String GREEN = "green";
    public final static String YELLO = "yello";


    private String color;

    //定义枚举
    @Retention(RetentionPolicy.RUNTIME)
    @StringDef({RED, GREEN, YELLO})
    public @interface FlagColor {
    }


    public String setColor(@FlagColor String color) {
        return this.color = color;
    }

    @FlagColor
    public String getColor() {
        return color;
    }
}
