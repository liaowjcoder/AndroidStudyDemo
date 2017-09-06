package com.zeal.annotationdemo.annotation05;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by zeal on 2017/9/3.
 */

public class MyInjector {

    public static void inject(final Activity activity) {

        try {
            Class<? extends Activity> clazz = activity.getClass();

            Field[] fields = clazz.getFields();

            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                BindView annotation = field.getAnnotation(BindView.class);
                if (annotation != null) {
                    int value = annotation.value();
                    field.setAccessible(true);

                    View view = activity.findViewById(value);
                    field.set(activity, view);
                }
            }


            final Method[] methods = clazz.getMethods();

            for (int i = 0; i < methods.length; i++) {
                final Method m = methods[i];
                OnClick annotation = m.getAnnotation(OnClick.class);
                if (annotation != null) {
                    final int[] value = annotation.value();

                    //动态代理生成OnclickListener对象，让所有的view点击事件都进入同一个方法
                    View.OnClickListener listener = (View.OnClickListener) Proxy.newProxyInstance(View.OnClickListener.class.getClassLoader(),
                            new Class[]{View.OnClickListener.class}, new InvocationHandler() {
                                @Override
                                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                                    Object result = m.invoke(activity,args);
                                    return result;
                                }
                            }
                    );


                    for (int k = 0; k < value.length; k++) {
                        int viewId = value[k];
                        View view = activity.findViewById(viewId);
                        view.setOnClickListener(listener);

                    }
                }
            }
        } catch (
                Exception e)

        {
            e.printStackTrace();
        }

    }
}
