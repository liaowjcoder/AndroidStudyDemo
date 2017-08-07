package com.example.singleton;

/**
 * Created by zeal on 2017/8/7.
 */
//饿汉式实现的单例模式
public class HungurySingleton {

    private static HungurySingleton INSTANCE = new HungurySingleton();


    private HungurySingleton() {
        System.out.println("instance create...");
    }


    //暴露给外界获取当前实例的接口
    public static HungurySingleton getINSTANCE() {
        return INSTANCE;
    }

    public static void createString() {
        System.out.println("create string...");
    }

    public static void main(String[] args) {

        //我们在这里并没有使用到HungurySingleton这个类，但是JVM去加载这个HungurySingleton就会创建
        //该类的实例，因此这就引发一个问题：该对象不管有没有使用，JVM去加载这个类时就会创建该类的实例
        //不足之处就是在于：无法对instance实例进行延时加载~
        //为什么需要延时加载呢？
        //注意：该对象的创建可以是比较耗时的，因此我们会考虑在有需要用到的地方才去加载
        //创建这个类实例。
        //instance create...
        //create string...
        HungurySingleton.createString();
    }
    
}
