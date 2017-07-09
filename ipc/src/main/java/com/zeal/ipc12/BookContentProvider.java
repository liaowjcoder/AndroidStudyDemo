package com.zeal.ipc12;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

/**
 * Created by liaowj on 2017/7/7.
 *
 *
 * 增删改查方法是在线程池中执行的，所以存在并发访问问题，但是这里使用一个数据库去管理，它
 * 内部做了同步处理，解决了并发访问的问题。（多个数据库除外）
 */

public class BookContentProvider extends ContentProvider {

    private SQLiteDatabase database;

    private Context mContext;

    private static final String AUTHRITY = "com.zeal.ipc12.provider";

    //uri
    public static final Uri BOOK_CONTENT_URI = Uri.parse("content://" + AUTHRITY + "/book");
    public static final Uri USER_CONTENT_URI = Uri.parse("content://" + AUTHRITY + "/user");

    //uri code
    private final static int BOOK_URI_CODE = 0;
    private final static int USER_URI_CODE = 1;


    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    //建立 uri 和 uricode 的关联关系
    {
        uriMatcher.addURI(AUTHRITY, "book", BOOK_URI_CODE);
        uriMatcher.addURI(AUTHRITY, "user", USER_URI_CODE);
    }


    public String getTableName(Uri uri) {

        int uriCode = uriMatcher.match(uri);
        String tableName = null;
        switch (uriCode) {
            case BOOK_URI_CODE:
                tableName = DbOpenHelper.BOOK_TABLE_NAME;
                break;
            case USER_URI_CODE:
                tableName = DbOpenHelper.USER_TABLE_NAME;
                break;
        }

        return tableName;
    }

    //主线程
    @Override
    public boolean onCreate() {
        mContext = getContext();
        initProviderData();
        return true;
    }

    private void initProviderData() {
        DbOpenHelper dbOpenHelper = new DbOpenHelper(getContext());
        database = dbOpenHelper.getWritableDatabase();

        database.execSQL("delete from " + DbOpenHelper.BOOK_TABLE_NAME);
        database.execSQL("delete from " + DbOpenHelper.USER_TABLE_NAME);

        //插入数据
        database.execSQL("insert into book values(1,'Android')");
        database.execSQL("insert into book values(2,'iOS')");
        database.execSQL("insert into book values(3,'H5')");
        database.execSQL("insert into book values(4,'ORACLE')");
        database.execSQL("insert into book values(5,'JS')");

        database.execSQL("insert into user values(1,'zeal',1)");
        database.execSQL("insert into user values(2,'Robin',1)");


    }

    //下面四个方法运行在 Binder 线程池中。
    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {


        String tableName = getTableName(uri);
        if (TextUtils.isEmpty(tableName)) {
            throw new IllegalArgumentException();
        }


        long insert = database.insert(tableName, null, values);
        //通知外界该数据库发生了改变，第二个参数表示观察者，这里传入null表示通知该uri的所有观察者
        mContext.getContentResolver().notifyChange(uri, null);
        return uri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {


        String tableName = getTableName(uri);

        if (TextUtils.isEmpty(tableName)) {
            throw new IllegalArgumentException();
        }

        int count = database.delete(tableName, selection, selectionArgs);

        if (count > 0) {
            //大于0表示删除成功
            mContext.getContentResolver().notifyChange(uri, null);
        }

        return count;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {

        String tableName = getTableName(uri);

        if (TextUtils.isEmpty(tableName)) {
            throw new IllegalArgumentException();
        }


        int raw = database.update(tableName, values, selection, selectionArgs);

        if (raw > 0) {
            mContext.getContentResolver().notifyChange(uri, null);
        }

        return raw;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        //根据 uri 获取需要查询的表名

        String tableName = getTableName(uri);

        if (tableName == null) {
            throw new IllegalArgumentException();
        }

        return database.query(tableName, projection, selection, selectionArgs, null, null, sortOrder, null);
    }

    //返回一个请求 uri 对应的 mime 类型
    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return "*/*";
    }


}
