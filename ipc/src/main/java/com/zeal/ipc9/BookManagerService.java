package com.zeal.ipc9;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by liaowj on 2017/7/6.
 * <p>
 * 接口回调通知客户端新书到达
 */

public class BookManagerService extends Service {
    private CopyOnWriteArrayList<Book> mBookList = new CopyOnWriteArrayList<>();
    private CopyOnWriteArrayList<IOnNewBookArrivedListener> mListenerList = new CopyOnWriteArrayList<>();
    private AtomicBoolean mIsServiceDestroyed = new AtomicBoolean(false);

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    private Binder binder = new IBookManager.Stub() {
        @Override
        public void addBook(Book book) throws RemoteException {
            mBookList.add(book);
        }

        @Override
        public List<Book> getBookList() throws RemoteException {
            return mBookList;
        }

        //注册
        @Override
        public void registerNewBookListener(IOnNewBookArrivedListener listener) {
            if (listener != null) {
                if (!mListenerList.contains(listener)) {
                    mListenerList.add(listener);
                } else {
                    Log.e("zeal", "register already exist");
                    return;
                }

                Log.e("zeal", "register listener success" + listener.toString());
            }
        }

        //反注册
        public void unregisterNewBookListener(IOnNewBookArrivedListener listener) {
            if (listener != null) {
                if (mListenerList.contains(listener)) {
                    mListenerList.remove(listener);
                } else {
                    Log.e("zeal", "unregister fail ," + listener.toString() + " no exist");
                    return;
                }
                Log.e("zeal", "unregisterNewBookListener success " + listener.toString());
            }
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();

        mBookList.add(new Book(1, "JAVA多线程并发核心技术"));
        mBookList.add(new Book(2, "JAVA编程思想"));


        new Thread(new ServiceWork()).start();
    }


    private class ServiceWork implements Runnable {

        @Override
        public void run() {

            while (true) {
                if (!mIsServiceDestroyed.get()) { //检测服务是否已经销毁
                    try {
                        Thread.sleep(4000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    int id = mBookList.size() + 1;
                    Book book = new Book(id, "new Book#" + id);
                    //添加一本书
                    try {
                        IBookManager.Stub.asInterface(binder).addBook(book);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    try {
                        onNewBookArrive(book);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    //通知所有的接口进行回调新书已经到达
    private void onNewBookArrive(Book book) throws RemoteException {

        for (IOnNewBookArrivedListener listener : mListenerList) {

            listener.onNewBookArrived(book);

        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        //表示服务已经结束
        mIsServiceDestroyed.set(true);
    }
}
