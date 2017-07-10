package com.zeal.ipc14;

import android.os.IBinder;
import android.os.RemoteException;

import com.zeal.ipc14.ISecurityCenter;

/**
 * Created by liaowj on 2017/7/10.
 * 加解密 Binder
 */

public class SecurityCenterImpl extends ISecurityCenter.Stub {
    private static final char SECRET = '^';

    @Override
    public String encryt(String content) throws RemoteException {

        char[] chars = content.toCharArray();

        for (int i = 0; i < chars.length; i++) {

            chars[i] ^= SECRET;

        }
        return new String(chars);
    }

    @Override
    public String decrypt(String password) throws RemoteException {
        return encryt(password);
    }
}
