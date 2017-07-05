package com.example;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by liaowj on 2017/7/3.
 * 同步和异步请求 http://www.jianshu.com/p/3214ef86a52d
 */

public class OkDemo2 {
    public static void main(String[] args) throws IOException {

        OkHttpClient okHttpClient = new OkHttpClient();


        Request request = new Request.Builder()
//                .url("http://www.jianshu.com/search?q=handler内存泄露&page=1&type=note")
                .url("http://www.qq.com")
                .build();

        Call call = okHttpClient.newCall(request);

        //同步请求
        //内部会调用 client.dispatcher().executed(this);该方法只是将该 call 添加到集合中，然后在请求执行完毕
        //之后，client.dispatcher().finished(this);移除掉这个请求而已，真正执行请求的代码是在Response result = getResponseWithInterceptorChain();
        //中。因为 Call 内部封装了 request ，因此在执行请求时只需要获取封装在 Call 内部的 request 即可。
        Response response = call.execute();
        if (response.isSuccessful()) {
            System.out.println(response.body().string());
        }

        //异步请求，通过接口回调告知用户 http 的异步执行结果
        //AsyncCall 用于封装这个接口回调，它是一个 Runnable 的实现类，也就是说它是一个任务。
        //在 NamedRunnable 的 run 方法中回去调用 execute 方法。也就是调用 AsyncCall 的 execute 方法
        //在 execute 方法内部主要做了3件事：
        //1.Response response = getResponseWithInterceptorChain();得到一个 response 响应
        //2.responseCallback.onResponse 或者 responseCallback.onFailure
        //3.client.dispatcher().finished(this);
        //而是谁来开启这个异步任务的呢？
        //在 RealCall.enqueue()内部会调用client.dispatcher().enqueue(new AsyncCall(responseCallback)); 方法，
        //该方法就是将 AsyncCall 添加到 Dispatcher 中 runningAsyncCalls 或者 readyAsyncCalls 集合中。
        //在 runningAsyncCalls 中的代码会被马上执行： executorService().execute(call);
        //在 readyAsyncCalls 中的 AsyncCall 排队等待执行。
        //当任务被执行时， AsyncCall 中的 execute 方法就会被执行，也就是上面说的 3 件事就会被执行。
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                System.out.println(e.getMessage());
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                if (response.isSuccessful()) {
//                    System.out.println(response.body().string());
//                }
//            }
//        });
    }
}
