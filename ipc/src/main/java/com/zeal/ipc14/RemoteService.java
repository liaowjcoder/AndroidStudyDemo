package com.zeal.ipc14;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by liaowj on 2017/7/10.
 * 远程服务，在 onBind 中返回指定的 Binder 对象
 */

public class RemoteService extends Service {

    //BinderPool的真正实现
    private Binder mBinderPool = new BinderPool.BinderPoolImpl();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinderPool;
    }
}
