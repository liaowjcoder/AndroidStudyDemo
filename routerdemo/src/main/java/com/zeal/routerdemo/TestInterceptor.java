package com.zeal.routerdemo;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AlertDialog;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;

/**
 * Created by zeal on 2017/8/5.
 */
@Interceptor(priority = 7)
public class TestInterceptor implements IInterceptor {
    @Override
    public void process(final Postcard postcard, final InterceptorCallback callback) {


        if ("/test/activity04".equals(postcard.getPath())) {
            final AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.getThis());

            dialog.setTitle("温馨提示");
            dialog.setMessage("想要跳转到Test4Activity么？");
            //不处理
            dialog.setNegativeButton("继续", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    callback.onContinue(postcard);
                }
            });
            //不跳转
            dialog.setNeutralButton("算了", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    callback.onInterrupt(null);
                }
            });
            //拦截之后，给postcard加参数
            dialog.setPositiveButton("加点料", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    postcard.withString("extra", "我被拦截了，，，我是在拦截器中附加的参数");
                    callback.onContinue(postcard);
                }
            });

            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    dialog.create().show();
                }
            });


        }

    }

    @Override
    public void init(Context context) {

    }
}
