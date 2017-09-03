package com.example;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MyClass {

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        Class<?> proxyClass = Proxy.getProxyClass(Collection.class.getClassLoader(), Collection.class);

//        System.out.println(proxyClass);
        
        Class<?> superclass = proxyClass.getSuperclass();
        //java.lang.reflect.Proxy 代理的父类
//        System.out.println(superclass);


        //获取所有的构造方法
        Constructor<?>[] constructors = proxyClass.getConstructors();
        for (int i = 0; i < constructors.length; i++) {
            Constructor<?> constructor = constructors[i];

            StringBuilder sBuilder = new StringBuilder();
            Class<?>[] parameterTypes = constructor.getParameterTypes();

            for (int j = 0; j < parameterTypes.length; j++) {
                sBuilder.append(parameterTypes[j].getName() + ",");
            }
            if (parameterTypes != null && parameterTypes.length > 0) {
                sBuilder.deleteCharAt(sBuilder.length() - 1);
            }
//            System.out.println(constructor.getName() + "(" + sBuilder + ")");
        }


        //获取代理类的所有的方法
        //在这里打印了Collectin和Object的所有的方法
        Method[] methods = proxyClass.getMethods();
        for (int i = 0; i < methods.length; i++) {
            Method method = methods[i];
//            System.out.println(method.getName());
        }

        //创建对象
        //InvocationHandler h
//        Constructor<?> constructor = proxyClass.getConstructor(InvocationHandler.class);
//        Collection collection = (Collection) constructor.newInstance(new MyInvocationHandler());
        //在没有处理InvocationHandler中的invoke方法时，这里返回的collection，实际是返回的collection.toString()
        //因此返回null
        //System.out.println(collection);

//        collection.clear();
//        collection.size();


        //方式2：一次性就创建代理对象
//        Collection collection2 = (Collection) Proxy.newProxyInstance(
//                Collection.class.getClassLoader(), //
//                new Class[]{Collection.class}, //
//                new MyInvocationHandler());//

//        System.out.println("start-----");

//        collection2.add("1");
//        collection2.add("2");
//        collection.toString();
//        collection2.add("3");

        //System.out.println(collection2.size());
//        System.out.println("end----------");


        ArrayList target = new ArrayList();
        List collection1 = (List) getProxy(target, new Advice() {
            @Override
            public void methodCallBefore(Method method) {
                System.out.println("methodCallBefore方法在" + method.getName() + "方法前被调用拉");
            }

            @Override
            public void methodCallAfter(Method method) {
                System.out.println("methodCallAfter方法在" + method.getName() + "方法后被调用拉");
            }
        });

        collection1.add("java");
        collection1.addAll(new ArrayList());
        collection1.clear();
        collection1.toString();

    }

    private static class MyInvocationHandler implements InvocationHandler {
        private ArrayList target = new ArrayList();

        @Override
        public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
            long startTime = System.currentTimeMillis();
            System.out.println("before "+method.getName());
            Object result = method.invoke(target, objects);
            long endTime = System.currentTimeMillis();
//            System.out.println(endTime - startTime);
            System.out.println("after "+method.getName());
            return result;
        }
    }


    /**
     * 通用的获取代理类的方法
     * @param target
     * @param advice
     * @return
     */
    private static Object getProxy(final Object target, final Advice advice) {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                advice.methodCallBefore(method);
                Object result = method.invoke(target, objects);
                advice.methodCallAfter(method);
                return result;
            }
        });
    }

    /*
    插入建议
     */
    private interface Advice {
        void methodCallBefore(Method method);

        void methodCallAfter(Method method);


    }


}
