package com.zeal.ipc14;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import java.util.concurrent.CountDownLatch;

/**
 * Created by liaowj on 2017/7/10.
 *
 *
 * 1.BinderPool 负责与远程服务建立连接，并且提供 queryBinder 方法由外界调用，
 * 根据外界提供的 binderCode 去调用远程服务中的 queryBinder 的方法得到真正
 * 需要调用的 Binder 对象（例如 ISecurityCenter/ICompute）。
 *
 * 2.BinderPool 远程服务 IBinderPool 提供了 DeathRecipient 失败重连机制。
 *
 * 3.BinderPoolImpl 就是 BinderPool 远程服务的返回的 Binder 对象。真正需要
 * 查询 Binder 的操作都会在这里实现。
 *
 */

public class BinderPool {

    private static BinderPool mInstance;
    private Context mContent;
    private IBinderPool mBinderPool;

    public static final int BINDER_CODE_SECURITY = 0;
    public static final int BINDER_CODE_COMPUTE = 1;
    private CountDownLatch mCountDownLatch;

    private BinderPool(Context context) {
        this.mContent = context.getApplicationContext();
        connectBinderPoolService();
    }

    public static BinderPool getInstance(Context context) {

        if (mInstance == null) {
            synchronized (BinderPool.class) {
                if (mInstance == null) {
                    mInstance = new BinderPool(context);
                }
            }
        }
        return mInstance;

    }


    private synchronized void connectBinderPoolService() {
        mCountDownLatch = new CountDownLatch(1);
        //开启远程服务
        Intent service = new Intent(mContent, RemoteService.class);
        mContent.bindService(service, conn, Service.BIND_AUTO_CREATE);
        try {
            mCountDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mBinderPool = IBinderPool.Stub.asInterface(service);

            try {
                mBinderPool.asBinder().linkToDeath(new IBinder.DeathRecipient() {
                    @Override
                    public void binderDied() {
                        Log.e("zeal", "binder is die");
                        mBinderPool.asBinder().unlinkToDeath(this, 0);

                        mBinderPool = null;
                        //重新连接
                        connectBinderPoolService();
                    }
                }, 0);
            } catch (RemoteException e) {
                e.printStackTrace();
            }

            mCountDownLatch.countDown();

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    public IBinder queryBinder(int binderCode) throws RemoteException {

        IBinder binder = null;

        if (mBinderPool != null) {

            //调用远程的 IBinderPool#queryBinder 查询对应的 Binder
            binder = mBinderPool.queryBinder(binderCode);

            return binder;

        }
        return null;
    }

    /*
    Binder 连接池的具体实现。
     */
    public static class BinderPoolImpl extends IBinderPool.Stub {
        public BinderPoolImpl(){
            super();
        }
        @Override
        public IBinder queryBinder(int bindCode) throws RemoteException {
            Binder binder = null;

            switch (bindCode) {
                case BINDER_CODE_SECURITY:
                    binder = new SecurityCenterImpl();
                    break;
                case BINDER_CODE_COMPUTE:
                    binder = new ComputeImpl();
                    break;
                default:
                    break;
            }

            return binder;
        }
    }
}
