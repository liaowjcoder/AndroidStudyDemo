package com.zeal.webviewdemo;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.JavascriptInterface;

public class JSInterface {

    private Context mContext;

    public JSInterface(Context context) {
        mContext = context;
    }

    /*
    定义需要被h5调用的方法
     */
    @JavascriptInterface
    public void callNativeMethod(int a, float b, String c, boolean d) {
        String strMessage = "-" + (a + 1) + "-" + (+1) + "-" + c + "-" + d;
        Log.e("zeal", strMessage);
    }


    /*
    定义需要被h5调用的方法
    分发器

    注意，在协议中定义这些简单数据类型的时候，String 是不需要指定类型的，
    这是使用 最广泛的类型。对于 int、Double 等简单类型，我们要在值前面加
    上类似 (int) 这样的约定， 这样才能在解析时不出问题。
     */
    @JavascriptInterface
    public void goToAnyWhere(String url) {

//        Log.e("zeal","gotoanywhere:"+url);
        if (TextUtils.isEmpty(url)) {
            return;
        }

        String packName = getPackageName(url);


        if (TextUtils.isEmpty(packName)) {
            return;
        }

        Intent intent = new Intent();


        //获取参数

        int pos = url.indexOf(":");

        if (pos != -1) {
            String strParams = url.substring(pos);
            String[] pairs = strParams.split("&");

            for (String s : pairs) {

                String[] arr = s.split("=");
                //key
                String key = arr[0];
                //value
                String value = arr[1];

                if (value.startsWith("(int)")) {
                    intent.putExtra(key, Integer.parseInt(value.substring(5)));
                } else if (value.startsWith("(Double)")) {
                    intent.putExtra(key, Double.parseDouble(value.substring(8)));
                } else {
                    intent.putExtra(key, value);
                }
            }
            try {
                intent.setClass(mContext, Class.forName(packName));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            //Calling startActivity() from outside of an Activity  context requires
            // the FLAG_ACTIVITY_NEW_TASK flag. Is this really what you want?
            mContext.startActivity(intent);
        }


    }

    public String getPackageName(String key) {
        String packageNmae;
        //解析出第一段：跳转的类名
        if (TextUtils.isEmpty(key)) {
            return null;
        }

        int pos = key.indexOf(",");

        if (pos != -1) {
            packageNmae = key.substring(0, pos);
        } else {
            packageNmae = key;
        }
        return packageNmae;
    }

}