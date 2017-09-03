package com.example;

import java.util.ArrayList;

/**
 * Created by zeal on 2017/9/3.
 */

public class Demo2 {

    public static void main(String[] args) {

        ClassLoader classLoader = System.class.getClassLoader();
        //输出null，表示System类是由BootStrapClassLoader加载的
        System.out.println("classloader:"+classLoader);


        //输出null，表示String类是由BootStrapClassLoader加载的rt.jar
        ClassLoader classLoader1 = String.class.getClassLoader();
        System.out.println("classload1:"+classLoader1);


        //classloader:sun.misc.Launcher$AppClassLoader@232204a1
        ClassLoader classLoader2 = Demo2.class.getClassLoader();
        System.out.println("classloader:"+classLoader2);
        while (classLoader2!=null) {
            System.out.println(classLoader2);
            classLoader2 = classLoader2.getParent();
        }

        ClassLoader classLoader3 = ArrayList.class.getClassLoader();
        System.out.println(classLoader3);


    }
}
