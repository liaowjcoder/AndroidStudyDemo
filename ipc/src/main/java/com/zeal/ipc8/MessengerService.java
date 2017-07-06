package com.zeal.ipc8;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;

/**
 * 服务端收到客户端的消息之后，回应客户端
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

                    //回应客户端
                    //拿到这个 msg 对应的 Messenger
                    Messenger client = msg.replyTo;

                    //构造一个你要发送的消息
                    Message message=Message.obtain();
                    message.what=2;

                    Bundle data = new Bundle();
                    data.putString("msg","服务端收到了客户端的消息拉");

                    message.setData(data);

                    //通过 Messenger 发送消息给客户端
                    try {
                        client.send(message);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
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
