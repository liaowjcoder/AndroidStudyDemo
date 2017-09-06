package com.zeal.annotationdemo.annotation02;

import java.lang.reflect.Method;

/**
 * Created by liaowj on 2017/7/17.
 */

public class Test {

    public static void main(String[] args) {

        try {
            Method method = Hello.class.getDeclaredMethod("hello");

            AnnotationFile annotation = method.getAnnotation(AnnotationFile.class);

            String message = annotation.getMessage();

            //指定了默认值的方法
            //测试 AnnotationFile 注解方法 getMessage 默认值：hello java
            System.out.println("测试 AnnotationFile 注解方法 getMessage 默认值："+message);

            //没有指定默认值的方法
            //测试 AnnotationFile 注解方法 getNickName 默认值：Sun java
            //如果在AnnotationFile没有指定 getNickName() 方法的默认值的话，那么在 Hello 中的 hello 的注解中就要强制指定
            String nickname = annotation.getNickName();
            System.out.println("测试 AnnotationFile 注解方法 getNickName 默认值："+nickname);

            //数组类型
            //1-2-3-4-
            int[] array = annotation.getArray();
            for (int i = 0; i < array.length; i++) {
                System.out.print(array[i]+"-");
            }
            System.out.println();

            //annotation
            //AnnotationTest name:AnnotationTest
            AnnotationTest annotationTest = annotation.annotation();
            String name = annotationTest.value();
            System.out.println("AnnotationTest name:"+name);




        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }


}