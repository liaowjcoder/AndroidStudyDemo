package com.zeal.broadcastreceivermodule.broadcast01;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/*
广播接受者

广播的传递的数据是放在intent里的，因此可以在intent取出数据之后再做其他的一些操作。

广播发送方分为普通广播和有序广播；
1.同步广播：发送方发出后，几乎同时到达多个广播接收者处，某个接收者不能接收到广播后进行一番处理后传给下一个接收者，
并且无法终止广播继续传播；Context.sendBroadcast(intent);
2.有序广播：广播接收者需要提前设置优先级，优先级高的先接收到广播，优先级数值为-1000~1000，
在AndroidManifest.xml的<intent-filter android:priority="xxx">设置；比如存在3个广播接收者A、B、C，优先级A>B>C,因此A最先收到广播，当A收到广播后，可以向广播中添加一些数据给下一个接收者(intent.putExtra())，或者终止广播(abortBroadcast())；Context.sendOrderedBroadcast(intent);

 */
public class MyReceiver extends BroadcastReceiver {
    //当广播接收者onReceive方法需要执行很长时间时，最好将此耗时工作通过Intent发送给Service，由Service完成
    @Override
    public void onReceive(Context context, Intent intent) {
        //MyReceiver1收到广播啦：com.zeal.broadcast02.Main2Activity.Broadcast
        //1501049342544
        Log.e("zeal", "MyReceiver1收到广播啦：" + intent.getAction() + "\n" + System.currentTimeMillis());
    }
}