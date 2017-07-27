package com.zeal.broadcastreceivermodule.broadcast02;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.zeal.broadcastreceivermodule.R;

public class MainActivity extends AppCompatActivity {
    private MyReceiver mReceiver;
    private TextView mText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mText = (TextView) findViewById(R.id.text);

        LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(this);

        mReceiver = new MyReceiver();

        IntentFilter intentFilter = new IntentFilter("com.zeal.action");
        broadcastManager.registerReceiver(mReceiver, intentFilter);
    }


    private class MyReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.e("zeal", "收到广播：" + intent.getAction());
            mText.setText(intent.getAction());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mReceiver != null) {
            LocalBroadcastManager.getInstance(this).unregisterReceiver(mReceiver);
        }
    }

    public void start2Activity(View view) {
        startActivity(new Intent(this,Main2Activity.class));
    }
}
