package com.zeal.memoryoptimization;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StringBuilder str = new StringBuilder();

        //ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        //int memoryClass = am.getMemoryClass();//以m为单位
        //int largeMemoryClass = am.getLargeMemoryClass();//以m为单位

        //memoryClass:96
        //argeMemoryClass:256
        //str.append("memoryClass:" + memoryClass + "\n" + "largeMemoryClass:" + largeMemoryClass);

        //Log.i("zeal", str.toString());


        //totalMemory:2.0305557
        //freeMemory:0.3917694
        //maxMemory:96.0
        float totalMemory = (float) (Runtime.getRuntime().totalMemory() * 1.0f / 1024 / 1024);
        float freeMemory = (float) (Runtime.getRuntime().freeMemory() * 1.0f / 1024 / 1024);
        float maxMemory = (float) (Runtime.getRuntime().maxMemory() * 1.0f / 1024 / 1024);
        str.append("totalMemory:" + totalMemory + "\n" + "freeMemory:" + freeMemory
                + "\n" + "maxMemory:" + maxMemory
        );

        Log.i("zeal", str.toString());

    }
}
