package com.example;

import java.net.URL;
import java.net.URLClassLoader;

public class Demo {

    public static void main(String[] args) {


        java.lang.Class demoClass = Demo.class;

        ClassLoader classLoader = demoClass.getClassLoader();

        //classloader:sun.misc.Launcher$AppClassLoader@232204a1
        System.out.println("classloader:"+classLoader);


        URL[] urLs = ((URLClassLoader) (classLoader)).getURLs();

        print(urLs);
    }

    private static void print(URL[] urLs) {


        for (int i = 0; i < urLs.length; i++) {


        }

    }
}
