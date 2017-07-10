package com.zeal.ipc14;

import android.os.RemoteException;

/**
 * Created by liaowj on 2017/7/10.
 * 计算 Binder
 */

public class ComputeImpl extends ICompute.Stub {
    @Override
    public int add(int x, int y) throws RemoteException {
        return x + y;
    }
}
