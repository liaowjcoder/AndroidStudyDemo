package com.example;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class Test {
    public static void main(String[] args) throws Exception {
        //获取Person的运行时Class实例的三种方式
        Person person = new Person();

        //方式1
        Class<? extends Person> pClass = person.getClass();

        //方式2
        Class<?> pClass2 = Class.forName("com.example.Person");

        //方式3
        Class<Person> pClass3 = Person.class;

        //应用场景1：
        //包名：com.example;完整类名：com.example.Person
        System.out.println("包名：" + pClass.getPackage().getName() + ";完整类名：" + pClass.getName());
        //应用场景2：创建对象 newInstance() 这种方式是创建无参构造对象
        Person p = (Person) pClass2.newInstance();
        //Person{age=0, name='null'}
        System.out.println(p);
        //应用场景3：获取Construct创建有参构造对象
        Constructor<?> constructor = pClass2.getConstructor(int.class, String.class);
        p = (Person) constructor.newInstance(25, "zeal");
        //Person{age=25, name='zeal'}
        System.out.println(p);

        //应用场景4：反射调用对象中的方法
        Method doSomething = pClass2.getDeclaredMethod("doSomething");
        doSomething.setAccessible(true);//java.lang.IllegalAccessException
        doSomething.invoke(pClass2.newInstance());

    }

}
