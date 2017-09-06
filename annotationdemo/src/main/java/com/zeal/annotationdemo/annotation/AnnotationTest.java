package com.zeal.annotationdemo.annotation;

import java.util.HashSet;

/**
 * Created by zeal on 2017/9/2.
 */
/*
注解就是给 java 代码加上一个标识规则，javac编译器在编译时就会去检测使用了该注解类的类是否符合标识规则，
从而提示程序员该使用了注解类的类的相关功能。

liaoweijiandeMacBook-Pro:annotation zeal$ javac AnnotationTest.java
注: AnnotationTest.java使用或覆盖了已过时的 API。
注: 有关详细信息, 请使用 -Xlint:deprecation 重新编译

编译 AnnotationTest.java 时，javac 编译器就会检测方法当中引用了过时的 API ，
告诉我们需要使用 deprescation 进行重新编译，也就是使用 @SuppressWarning
进行压制警告。
 */
public class AnnotationTest {

    @SuppressWarnings("deprecation")
    public static void main(String[] args) {

        //当使用了 SuppressWarning 之后，那么该方法的删除线就没有了
        System.runFinalizersOnExit(false);

        HashSet<Person> set = new HashSet<>();


        set.add(new Person(11, "jack"));
        set.add(new Person(21, "sena"));
        set.add(new Person(11, "jack"));
        set.add(new Person(12, "judy"));
        set.add(new Person(25, "zeal"));


        System.out.println(set.size());

    }
}
