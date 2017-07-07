package com.zeal.ipc11;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by liaowj on 2017/7/7.
 */

public class BookContentProvider extends ContentProvider {
    //主线程
    @Override
    public boolean onCreate() {
        Log.e("zeal", "BookContentProvider onCreate " + Thread.currentThread().getName());
        return false;
    }

    //下面四个方法运行在 Binder 线程池中。
    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        Log.e("zeal", "BookContentProvider insert " + Thread.currentThread().getName());
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        Log.e("zeal", "BookContentProvider delete " + Thread.currentThread().getName());
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        Log.e("zeal", "BookContentProvider update " + Thread.currentThread().getName());
        return 0;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Log.e("zeal", "BookContentProvider query " + Thread.currentThread().getName());
        return null;
    }

    //返回一个请求 uri 对应的 mime 类型
    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return "*/*";
    }


}
