package com.zeal.ipc14;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by liaowj on 2017/7/10.
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Thread() {
            @Override
            public void run() {
                super.run();
                doWork();
            }
        }.start();

    }

    private void doWork() {


        try {
            IBinder compueteIBinder = BinderPool.getInstance(this).queryBinder(BinderPool.BINDER_CODE_COMPUTE);

            ICompute compuet = ICompute.Stub.asInterface(compueteIBinder);

            int addResult = compuet.add(1, 2);

            Log.e("zeal", "add result：" + addResult);


            IBinder securityCenterBinder = BinderPool.getInstance(this).queryBinder(BinderPool.BINDER_CODE_SECURITY);
            ISecurityCenter securityCenter = ISecurityCenter.Stub.asInterface(securityCenterBinder);
            String password = securityCenter.encryt("hello -- 安卓");
            String decrypt = securityCenter.decrypt(password);
            Log.e("zeal", "encrypt:" + password);
            Log.e("zeal", "decrypt:" + decrypt);

        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }
}
