package com.zeal.ipc11;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by liaowj on 2017/7/7.
 *
 * ContentProvider 的入门
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //content://com.zeal.ipc11.provider 唯一标识
        Uri uri = Uri.parse("content://com.zeal.ipc11.provider");


        /*
        BookContentProvider onCreate main
        processName:com.zeal.ipc:remote
        BookContentProvider query Binder_2
        BookContentProvider query Binder_1
        BookContentProvider query Binder_2
         */

        //检测是否在同一个进程中调用
        getContentResolver().query(uri, null, null, null, null);
        getContentResolver().query(uri, null, null, null, null);
        getContentResolver().query(uri, null, null, null, null);


    }
}
