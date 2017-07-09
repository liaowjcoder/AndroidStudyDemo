package com.zeal.ipc13;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
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

public class TCPServerService extends Service {


    private boolean mIsServiceDestroyed;

    private String[] mDefineMessage = {"你好啊，哈哈",

            "深圳天气好吗", "你来自哪里啊", "你毕业于哪个大学啊"};

    public TCPServerService() {


    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mIsServiceDestroyed = true;
    }


    private class TCPServer implements Runnable {

        @Override
        public void run() {

            //创建一个服务端的 sokcet 准备接收客户端发送过来的消息
            try {
                ServerSocket ss = new ServerSocket(10086);

                //开始监听客户端的连接
                while (!mIsServiceDestroyed) {
                    //收到一个连接
                    final Socket client = ss.accept();

                    Log.e("zeal", "收到客户端请求：" + client);

                    new Thread() {
                        @Override
                        public void run() {
                            super.run();

                            try {
                                responseClient(client);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }
                    }.start();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        private void responseClient(Socket client) throws IOException {

            //接受客户端的消息
            BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));


            PrintWriter pw = new PrintWriter(new OutputStreamWriter(client.getOutputStream()), true);

            pw.println("欢迎来到聊天室");

            while (!mIsServiceDestroyed) {

                String line = br.readLine();

                Log.e("zeal", "msg from " + client + " " + line);

                if (TextUtils.isEmpty(line)) {
                    //客户端断开连接
                    break;
                }

                int i = new Random().nextInt(mDefineMessage.length);

                String msg = mDefineMessage[i];

                pw.println(msg);

            }
            Log.e("zeal", client + " quit");

            br.close();
            pw.close();
            client.close();

        }
    }
}
