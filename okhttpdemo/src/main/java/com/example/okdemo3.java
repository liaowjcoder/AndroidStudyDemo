package com.example;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.internal.framed.Header;
import okio.BufferedSink;

/**
 * Created by liaowj on 2017/7/5.
 * <p>
 * 利用 MultipartBody 进行文件上传
 * <p>
 * <p>
 * 在 MultipartBody 内部维护了一个 List<Part> parts; 的集合
 * 它表示：MultipartBody类型的请求体的每一条数据都会转化为一个 Multipart.Part 对象，然后添加到集合中。
 * <p>
 * 添加一个表单数据 name : jack ----> 构建一个 Part
 * 添加一个文件  file  -----> 构建一个 Part
 */

public class okdemo3 {

    public static void main(String[] args) throws IOException {

        //客户端是如何进行文件上传的

        OkHttpClient okHttpClient = new OkHttpClient.Builder()

                .build();
//        MultipartBody body = new MultipartBody.Builder()
//                .addFormDataPart("name","value")
//                .addFormDataPart("name","value", RequestBody.create(MediaType.parse(""),new File("")))
//                .addPart(new Headers.Builder().build(), RequestBody.create(MediaType.parse(""),new File("")))
//                //Part 内部会提供一系列的静态方法来创建一个 Part 对象
//                //.addPart(new MultipartBody.Part.create())
//                .build();

        //post a string
//        MediaType MEDIA_TYPE_MARKDOWN
//                = MediaType.parse("text/x-markdown; charset=utf-8");
//        String postBody = ""
//                + "Releases\n"
//                + "--------\n"
//                + "\n"
//                + " * _1.0_ May 6, 2013\n"
//                + " * _1.1_ June 15, 2013\n"
//                + " * _1.2_ August 11, 2013\n";
//        Request request = new Request.Builder()
//                .post(RequestBody.create(MEDIA_TYPE_MARKDOWN,postBody))
//                .url("https://api.github.com/markdown/raw")
//                .build();
//
//        Call call = okHttpClient.newCall(request);
//
//        try {
//            Response response = call.execute();
//            if(response.isSuccessful()) {
//                System.out.println(response.body().string());
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        //post a  stream
//        RequestBody body = new RequestBody() {
//            @Override
//            public MediaType contentType() {
//                return MediaType.parse("text/x-markdown; charset=utf-8");
//            }
//
//            //重写 write 方法，具体怎么写操作由你决定 sink 就相当于输出流
//            @Override
//            public void writeTo(BufferedSink sink) throws IOException {
//                sink.writeUtf8("Numbers\n");
//                sink.writeUtf8("-------\n");
//                for (int i = 1; i < 100; i++) {
//                    sink.writeUtf8(String.format("* %s = %s\n", i, i + 1));
//                }
//            }
//        };
//
//        Request request = new Request.Builder()
//                .post(body)
//                .url("https://api.github.com/markdown/raw")
//                .build();
//
//        Call call = okHttpClient.newCall(request);
//
//        try {
//            Response response = call.execute();
//            if (response.isSuccessful()) {
//                System.out.println(response.body().string());
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        //post a file
//        File file = new File("d://README.md");
//        Request request = new Request.Builder()
//                .post(RequestBody.create(MediaType.parse("text/x-markdown; charset=utf-8"),file))
//                .url("https://api.github.com/markdown/raw")
//                .build();
//
//        Call call = okHttpClient.newCall(request);
//
//        try {
//            Response response = call.execute();
//            if (response.isSuccessful()) {
//                System.out.println(response.body().string());
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        //post form parma
//        FormBody formBody = new FormBody.Builder()
//                .add("search", "liaowjcoder")
//                .build();
//        Request request = new Request.Builder()
//                .post(formBody)
//                .url("https://en.wikipedia.org/w/index.php")
//                .build();
//
//        Call call = okHttpClient.newCall(request);
//
//        try {
//            Response response = call.execute();
//            if (response.isSuccessful()) {
//                System.out.println(response.body().string());
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        //post multipart body
        MultipartBody body = new MultipartBody.Builder()
                //设置当前要添加的类型是 FORM 表单类型
                .setType(MultipartBody.FORM)
                .addFormDataPart("title", "Square Logo")
                //添加文件类型
                .addFormDataPart("image", "logo-square.png",
                        RequestBody.create(MediaType.parse("image/png"), new File("d://logo-square.png")))
                .build();

        Request request = new Request.Builder()
                .header("Authorization", "Client-ID ...")
                .url("https://api.imgur.com/3/image")
                .post(body)
                .build();
        Call call = okHttpClient.newCall(request);
//
        try {
            Response response = call.execute();
            if (response.isSuccessful()) {
                System.out.println(response.body().string());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
