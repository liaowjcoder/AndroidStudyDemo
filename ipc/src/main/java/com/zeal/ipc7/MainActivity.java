package com.zeal.ipc7;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.PersistableBundle;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by liaowj on 2017/7/6.
 * Messenger 传递数据时必须是 Message，而 Message 是实现了 Parcelable 接口的，可以跨进程传输。
 * 在传输 Message 时需要注意几点信息：
 *  Message：what,replyTo,arg1,arg2,Bundle在跨进程传输时都可以使用
 *  而 obj 字段在 Android 2.2 之前是不允许进行传进程传输的，而在 2.2 版本之后也只允许系统级别实现了 Parcelable 的对象
 *  也就是说自定义的 Parcelable 是无法通过 obj 这个属性进行传输的
 *
 *
 *
 *
 */

public class MainActivity extends AppCompatActivity {
    private Messenger mServiceMessenger;

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
            bundle.putString("msg","say hello to server");
            msg.setData(bundle);
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
        if(conn!=null) {
            unbindService(conn);
        }
    }
}
