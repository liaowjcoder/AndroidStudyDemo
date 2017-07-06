package com.zeal.ipc5;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by liaowj on 2017/7/6.
 */

public class MainActivity extends AppCompatActivity {

    private IBookManager mBookManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
                Log.e("zeal", mBookManager.getBookList().getClass().getSimpleName());


            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (conn != null) {
            unbindService(conn);
        }
    }
}
