package com.zeal.ipc8;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * 服务端给客户端进行回应：
 *
 *  客户端：
 *      定义 Handler 和 Messenger
 *      将客户端发送给服务端的 message.replyTo = 客户端创建的 Messenger （这样服务端就可以那这个 Messenger 发送数据给客户端了）
 *
 */

public class MainActivity extends AppCompatActivity {
    private Messenger mServiceMessenger;

    private static class GetMessageHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case 2:
                    Bundle data = msg.getData();
                    String result = data.getString("msg");
                    System.out.println("receive from server:"+result);

                    break;
            }
        }
    }

    private final Messenger mGetReplyMessenger = new Messenger(new GetMessageHandler());

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Intent intent = new Intent(this, MessengerService.class);

        //绑定服务
        bindService(intent, conn, Service.BIND_AUTO_CREATE);


    }

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //根据服务端传来的 IBinder 对象创建一个 Messenger
            mServiceMessenger = new Messenger(service);

            //当服务建立连接之后就发送数据给服务端
            Message msg = Message.obtain();
            msg.what = 1;
            //跨进程通讯不能使用 message.obj 这个属性，应该使用 bundle 否则会出现Can't marshal non-Parcelable objects across processes.
            //msg.obj = "say hello to server";
            Bundle bundle = new Bundle();
            bundle.putString("msg", "say hello to server");
            msg.setData(bundle);
            //将 Messenger 传递给服务端
            msg.replyTo = mGetReplyMessenger;
            //给远程服务发送消息
            try {
                mServiceMessenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (conn != null) {
            unbindService(conn);
        }
    }
}
