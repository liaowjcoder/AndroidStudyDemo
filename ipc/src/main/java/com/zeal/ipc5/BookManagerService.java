package com.zeal.ipc5;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by liaowj on 2017/7/6.
 * <p>
 * 现在就要实现上面定义的 AIDL 接口
 *
 * 继承 IBookManager.Stub 实现 aidl 方法（这些方法是在服务端的Binder线程池中执行的）
 * 因此如果有多个客户端同时连接这个远程服务的话，就需要处理并发访问问题,这里使用 CopyOnWriteArrayList
 * 来解决这个问题。
 */

public class BookManagerService extends Service {
    private CopyOnWriteArrayList<Book> mBookList = new CopyOnWriteArrayList<>();

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
    };

    @Override
    public void onCreate() {
        super.onCreate();

        mBookList.add(new Book(1,"JAVA多线程并发核心技术"));
        mBookList.add(new Book(2,"JAVA编程思想"));
    }
}
