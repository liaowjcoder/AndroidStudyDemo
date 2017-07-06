package com.zeal.ipc7;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.support.annotation.Nullable;

/**
 * Created by liaowj on 2017/7/6.
 * 服务端进程：
 * 1.创建一个 Service 用于客户端的连接。
 * 2.创建一个 Handler，用于处理客户端发送过来的 Message
 * 3.利用创创建的 Handler 创建一个 Messenger
 * 4.在 onBind 中返回这个 Messenger.getBinder 对应的 Binder 对象。
 *
 * 客户端进程
 * 1、绑定远程服务
 * 2、根据 IBinder 创建一个 Messenger 对象
 * 3、通过 Messenger 去发送一个消息（Message）
 *
 */

public class MessengerService extends Service {

    private static class MessengerHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case 1:
                    //接受客户端传递过来的消息
                    Bundle bundle = msg.getData();
                    System.out.println(bundle.getString("msg"));
                    break;
            }
        }
    }

    private final Messenger messenger = new Messenger(new MessengerHandler());


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }
}
