package com.zeal.ipc10;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.zeal.ipc.R;

/**
 * 解决方案就是使用 RemoteCallbackList 来保存进程间通讯的接口。
 *
 * 底层数据结构：ArrayMap
 * ArrayMap<IBinder, Callback> mCallbacks= new ArrayMap<IBinder, Callback>()
 * key:IBinder，支持任意的 AIDL 接口，Binder binder = listener.asBinder()
 * value:Callback 封装了真正的 listener Callback callback =new Callback(listener,cookie);
 *
 * 跨进程通讯传输客户端的对象每次都不一样的，但是传输这些对象底层的 Binder 是一样的
 * 因此可以使用 Binder 做为 key 来找到唯一的接口回调。
 *
 * 当远程服务中断时的监听：
 *
 *      方式1：onServiceDisconnected：主线程 main
 *      方式2：binderDied 线程池
 *
 */

public class MainActivity extends AppCompatActivity {

    private IBookManager mBookManager;

    private MyHandler mHandler = new MyHandler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);

        Intent service = new Intent(this, BookManagerService.class);
        bindService(service, conn, Service.BIND_AUTO_CREATE);
    }

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //将服务端传递过来的 IBinder 转化为实际的对象 IBookManager
            mBookManager = IBookManager.Stub.asInterface(service);
            try {
                //这里可能会造成线程阻塞
                Log.e("zeal", mBookManager.getBookList().toString());
                //ArrayList 虽然服务端发送的数据类型是CopyOnWriteArrayList但是Binder都会将其转化为ArrayList类型给客户端
                Log.e("zeal", mBookManager.getBookList().getClass().getSimpleName());

                mBookManager.registerNewBookListener(mNewBookArriveListener);

                // Binder 意外断开时回调
                mBookManager.asBinder().linkToDeath(new IBinder.DeathRecipient() {
                    @Override
                    public void binderDied() {
                        //在线程池中执行
                        Log.e("zeal","binderDied："+Thread.currentThread().getName());//binderDied：Binder_1
                    }
                },0);

            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            //在主线程中执行。
            Log.e("zeal","onServiceDisconnected："+Thread.currentThread().getName());//onServiceDisconnected：main
            mBookManager = null;
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (conn != null) {
            unbindService(conn);
        }

        if (mNewBookArriveListener != null) {
            try {
                mBookManager.unregisterNewBookListener(mNewBookArriveListener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        mBookManager = null;
    }

    public void addBook(View view) {

        Book book = new Book(3, "Android开发进阶");

        if (mBookManager.asBinder().isBinderAlive()) {
            try {
                //调用远程服务方法，添加一本书
                mBookManager.addBook(book);

                //打印图书列表
                Log.e("zeal", "AFTER ADD BOOK:" + mBookManager.getBookList().toString());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    //接口监听服务端的回调
    private IOnNewBookArrivedListener mNewBookArriveListener = new IOnNewBookArrivedListener.Stub() {
        @Override
        public void onNewBookArrived(Book newBook) throws RemoteException {
            Message message = Message.obtain();
            message.obj = newBook;
            mHandler.sendMessage(message);
        }
    };

    private class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            Book book = (Book) msg.obj;

            Log.e("zeal", "receive a new Book--" + book.toString());
        }
    }

}
