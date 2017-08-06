package com.zeal.broadcastreceivermodule.broadcast01;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.util.Log;

/*

 */
public class MyReceiver2 extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        //MyReceiver2收到广播啦：com.zeal.broadcast02.Main2Activity.Broadcast
        //1501049342562
        Log.e("zeal", "MyReceiver2收到广播啦：" + intent.getAction()+"\n"+ System.currentTimeMillis());

    }
}