package com.zeal.ipc9;

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
 * Created by liaowj on 2017/7/6.
 *
 * 注册：register listener success com.zeal.ipc9.IOnNewBookArrivedListener$Stub$Proxy@141e334a
 * 解除注册：unregister fail com.zeal.ipc9.IOnNewBookArrivedListener$Stub$Proxy@7eda3d8 no exist
 *
 * 可以发现：客户端传递过来的 listener 并不一样，但是检查了客户注册和解除注册使用的 listener 是同一个的，但是
 * 为什么在服务端却得到不一样的对象值呢？
 *
 * 原因：对象的传输本质就是序列化和反序列化的过程，这也是想要在进程间通讯的对象必须要实现 Parcelable 的原因。
 * 对象想要在进程间通讯，必须依赖于 Binder ，Binder 会对每一个需要进行传递的对象都做了处理。
 *
 * 解决方案就是使用 RemoteCallbackList 来保存进程间通讯的接口。
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

            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
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
