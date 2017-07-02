package com.example;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class okdemo {

    public static void main(String[] args) {

        testCache();

    }

    private static void testCache() {

        //缓存：okhttp 支持 get 请求的缓存
        //原因：缓存必须 key 必须唯一， get 的 url 是唯一的，而 post 的 url 是一样，只是 body 不一样，所以
        //okhttp 暂不支持 post 类型的缓存

        //缓存对应的 url 必须是支持缓存的吗？
        //不一定，为什么呢？【有待验证】因为 response 都有 cachecontroll ，在缓存策略器中会去判断 no-strore 字段是否支持
        //当我们第一次请求得到响应之后，就将其缓存起来，那么下次再发送请求的时候检测是否有缓存，有则直接读取缓存信息，没有则发送真正的请求。

        /*
        请求结果
        network response:Response{protocol=http/1.1, code=200, message=OK, url=http://www.qq.com/}
        cache response:null
        第二次直接从缓存中读取数据
        network response2:null
        cache response2:Response{protocol=http/1.1, code=200, message=OK, url=http://www.qq.com/}

        磁盘缓存的文件：
        7f4c79817fabaeaa0e909754cfe655e7.0
            //缓存响应的头部信息
            http://www.qq.com/
            GET
            1
            Accept-Encoding: gzip
            HTTP/1.1 200 OK
            14
            Server: squid/3.5.20
            Date: Sun, 02 Jul 2017 02:54:01 GMT
            Content-Type: text/html; charset=GB2312
            Transfer-Encoding: chunked
            Connection: keep-alive
            Vary: Accept-Encoding
            Expires: Sun, 02 Jul 2017 02:55:01 GMT
            Cache-Control: max-age=60
            Vary: Accept-Encoding
            Content-Encoding: gzip
            Vary: Accept-Encoding
            X-Cache: HIT from nanjing.qq.com
            OkHttp-Sent-Millis: 1498964041246
            OkHttp-Received-Millis: 1498964041330

        7f4c79817fabaeaa0e909754cfe655e7.1
            //缓存的内容就是一些乱七八糟的数据（经过编码后的数据）
        journal
            //disklrucache 日志文件
         */

        //几个问题：
        //1.同一个请求不可以被同一个 call 去执行，内部的 call 有一个属性 executed 当一个 reqeust 被执行的话，那么 executed 被置位 true 。
        //2.为什么想要缓存 response 必须先要要进行 response.body().string()才能将其缓存呢？
        //3.networkresponse 和 cacheresponse 分别表示什么？
        //4.怎么读取缓存和写入缓存？


//        String url = "http://www.qq.com";
        String url = "http://www.imooc.com/courseimg/s/cover005_s.jpg";


        Cache cache = new Cache(new File("/Users/zeal/Desktop/temp"),10*10*1024);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                //观测Call call = okHttpClient.newCall(request);源码，这三个超时时间分别是应用在哪里？
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .connectTimeout(15, TimeUnit.SECONDS)
                .cache(cache)
                .build();

        //noCache()： 就算是本地有缓存，也不会读缓存，直接访问服务器
        //noStore(): 不会缓存数据，直接访问服务器
        //onlyIfCached():只请求缓存中的数据，不靠谱
        final Request request = new Request.Builder()
                .url(url)
                .cacheControl(new CacheControl.Builder().maxStale(100,TimeUnit.SECONDS).maxAge(0,TimeUnit.SECONDS).build())
                .build();

        Call call = okHttpClient.newCall(request);


        try {
            //第一次发送请求
            Response response = call.execute();

//            第一次读取数据
            response.body().string();


            System.out.println("network response:"+response.networkResponse());

            System.out.println("cache response:"+response.cacheResponse());



            Call call2 = okHttpClient.newCall(request);
            //第二次发送请求
            Response response2 = call2.execute();

            //第二次读取数据
            response2.body().string();


            System.out.println("network response2:"+response2.networkResponse());

            System.out.println("cache response2:"+response2.cacheResponse());

            System.out.println("cache hitCount:"+cache.hitCount());//使用缓存的次数
            System.out.println("cache networkCount:"+cache.networkCount());//使用网络请求的次数
            System.out.println("cache requestCount:"+cache.requestCount());//请求的次数

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
