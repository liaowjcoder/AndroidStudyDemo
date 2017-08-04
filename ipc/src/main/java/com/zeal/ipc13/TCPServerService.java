package com.zeal.ipc13;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

/**
 * Created by liaowj on 2017/7/10.
 * 远程服务端
 * 1.创建线程去接受客户端端连接；
 * 2.监听到有客户端 socket 连接时，开启子线程进行消息通讯。
 * 3.关闭 socket
 */

public class TCPServerService extends Service {
    private boolean mIsServiceDestryed;

    private static final String[] mDefineMsg = {
            "今天天气好吗？",
            "你来自哪里啊？",
            "深圳现在房价行情怎样啊？",
            "你喜欢听谁的歌啊"

    };

    @Override
    public void onCreate() {
        new Thread(new TCPRunnable()).start();
        super.onCreate();


    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    class TCPRunnable implements Runnable {
        @Override
        public void run() {
            //创建服务端 socket
            try {
                ServerSocket serverSocket = new ServerSocket(10086);


                while (!mIsServiceDestryed) {

                    //等待客户端连接
                    final Socket client = serverSocket.accept();
                    Log.e("zeal", "client：" + client + " connect");
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            responseClient(client);
                        }
                    }).start();

                }
            } catch (IOException e) {
                e.printStackTrace();
                //出异常的情况
                Log.e("zeal", "服务端 socket 创建失败");
                return;
            }
        }
    }

    /*
    开始和该客户端进行通讯。
     */
    private void responseClient(Socket client) {

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            //PrintWriter构造参数必须传入 true 参数，因为下面会使用到 println 方法，有这个参数表示它会自动将
            //刷新輸出缓冲区。
            PrintWriter out = new PrintWriter(new OutputStreamWriter(client.getOutputStream()),true);

            //给客户端发消息：欢迎来到聊天室
            Log.e("zeal","欢迎来到聊天室");
            out.println("欢迎来到聊天室");


            while (!mIsServiceDestryed) {

                String msg = in.readLine();

                if (TextUtils.isEmpty(msg)) {
                    //断开连接
                    return;
                }
                Log.e("zeal", "receive msg from client:" + msg);


                //服务端回复消息给客户端
                int index = new Random().nextInt(mDefineMsg.length);
                String replyMsg = mDefineMsg[index];


                out.println(replyMsg);

            }

            //关闭 socket
            in.close();
            out.close();
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mIsServiceDestryed = true;
    }
}
