package com.zeal.ipc12;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by zeal on 2017/7/9.
 * 数据库管理类:数据库的创建，管理和升级
 *
 * BookProvider 图书的内容提供者，底层通过该数据库对外界暴露数据
 *
 * Uri 一一对应哪个 UriCode 有了 UriCode 就可以找到需要操作哪张数据表。
 *
 *
 *
 */

public class DbOpenHelper extends SQLiteOpenHelper {

    //数据库名
    private static final String DB_NAME = "book_provider.db";

    //图书表名
    public static final String BOOK_TABLE_NAME = "book";

    //用户表名
    public static final String USER_TABLE_NAME = "user";

    //创建 book 数据表
    private static final String CREATE_BOOK_TABLE = "CREATE TABLE IF NOT EXISTS " +
            BOOK_TABLE_NAME + "( _id INTEGER PRIMARY KEY ,name TEXT)";


    //创建 user 数据表
    private static final String CREATE_USER_TABLE = "CREATE TABLE IF NOT EXISTS " +
            USER_TABLE_NAME + " (_id INTEGER PRIMARY KEY ,name TEXT, sex INT )";


    private static final int DB_VERSION = 1;

    public DbOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //执行两条 sql 语句去创建数据表
        db.execSQL(CREATE_BOOK_TABLE);
        db.execSQL(CREATE_USER_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
