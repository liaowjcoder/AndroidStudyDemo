package com.zeal.ipc10;

import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by liaowj on 2017/7/6.
 * <p>
 * RemoteCallbackList 接口回调通知客户端新书到达
 */

public class BookManagerService extends Service {
    private CopyOnWriteArrayList<Book> mBookList = new CopyOnWriteArrayList<>();
    //private CopyOnWriteArrayList<IOnNewBookArrivedListener> mListenerList = new CopyOnWriteArrayList<>();
    private AtomicBoolean mIsServiceDestroyed = new AtomicBoolean(false);
    private RemoteCallbackList<IOnNewBookArrivedListener> mRemoteCallList = new RemoteCallbackList<>();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
    //在 server 端创建一个 Binder 对象。
    private Binder binder = new IBookManager.Stub() {

        //校验客户端调用的合法性


        @Override
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {

            //校验是否注册了该服务

            int check = checkCallingOrSelfPermission("com.zeal.ipc.permission.ACCESS_BOOK_SERVICE");
            Log.e("zeal", "onTransact：" + (check != PackageManager.PERMISSION_DENIED));
            if (check == PackageManager.PERMISSION_DENIED) {
                return false;
            }
            //校验包名
            String packageName = null;
            //根据 uid 获取所有的 package
            //Linux uid uid可以用于权限校验工作
            //packages一般都会返回一个数据，如果多个数据的原因是多个多个包通过shareid共享一个 user id
            String[] packages = getPackageManager().getPackagesForUid(getCallingUid());

            if (packages != null && packages.length > 0) {
                packageName = packages[0];

                if (!packageName.startsWith("com.zeal")) {
                    return false;
                }
            }
            Log.e("zeal", "onTransact packageName：" + packageName);//com.zeal.ipc


            return super.onTransact(code, data, reply, flags);
        }

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
            mRemoteCallList.register(listener);
            Log.e("zeal", "REGISTER:" + listener);
            Log.e("zeal", "AFTER REGISTER:" + mRemoteCallList.getRegisteredCallbackCount());
        }

        //反注册
        public void unregisterNewBookListener(IOnNewBookArrivedListener listener) {
            mRemoteCallList.unregister(listener);
            Log.e("zeal", "UNREGISTER:" + listener);
            Log.e("zeal", "AFTER UNREGISTER:" + mRemoteCallList.getRegisteredCallbackCount());
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

//        for (IOnNewBookArrivedListener listener : mListenerList) {
//
//            listener.onNewBookArrived(book);
//
//        }

        mBookList.add(book);
        //配对使用
        int n = mRemoteCallList.beginBroadcast();

        for (int i = 0; i < n; i++) {

            IOnNewBookArrivedListener item = mRemoteCallList.getBroadcastItem(i);

            if (item != null) {
                item.onNewBookArrived(book);
            }

        }
        //配对使用
        mRemoteCallList.finishBroadcast();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        //表示服务已经结束
        mIsServiceDestroyed.set(true);
    }
}
