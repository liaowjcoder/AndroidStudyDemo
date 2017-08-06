package com.zeal.broadcastreceivermodule.broadcast01;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.zeal.broadcastreceivermodule.R;
import com.zeal.broadcastreceivermodule.broadcast02.Main2Activity;

public class MainActivity extends AppCompatActivity {
    private MyReceiver mReceiver;
    private TextView mText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mText = (TextView) findViewById(R.id.text);



    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void send(View view) {
        sendBroadcast(new Intent("com.zeal.broadcast02.Main2Activity.Broadcast"));
    }
}
