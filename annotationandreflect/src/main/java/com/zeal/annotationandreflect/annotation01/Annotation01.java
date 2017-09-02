package com.zeal.annotationandreflect.annotation01;

import java.lang.reflect.Method;

/**
 * Created by liaowj on 2017/7/17.
 * 根据设置的 RetentionPolicy ，Taget注解的信息被保留到不同的阶段：
 * 1.当 Target 为 RUNTIME 时：表示注解信息会被保留到运行时期，这是可以通过反射获取该注解的信息
 *  Javac 会将源文件.java 编译为 .class 文件，然后通过 ClassLoader 将 .class 文件加载到内存中
 *  成为字节码，这时就可以通过反射来获取对应的注解信息。
 *          打印结果：target:@com.zeal.annotationandreflect.annotation01.Target()
 * 2.当 Target 为 SOURCE 时：表示注解信息只会被保留到编译时期
 *          打印结果：没有该target注解
 * 3.当 Target 为 CLASS 时：表示注解信息会被保留到编译时期,该注解信息会被编译，但不会被虚拟机加载到内存中，
 *  保留到 Javac 将 .java 文件编译成 .class 文件，但是不会随着 ClassLoader 将该 .class 文件加载到内存中
 *
 *          打印结果：没有该target注解
 *
 * 这里只有 RUNTIME 修饰才可以在运行时获取该注解信息，因为 DoSomething.class 返回的是 Class 是 DoSomething
 * 在运行时类。
 */

public class Annotation01 {

    public static void main(String[] args) {


        try {
            Method method = DoSomething.class.getMethod("doSomething");
            Target target = method.getAnnotation(Target.class);
            if (target != null) {
                System.out.print("target:" + target);
            } else {
                System.out.print("没有该target注解");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
