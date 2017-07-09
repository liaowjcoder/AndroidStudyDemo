package com.zeal.ipc12;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.zeal.ipc.R;

public class ProviderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider);


        Uri bookUri = BookContentProvider.BOOK_CONTENT_URI;
        Uri userUri = BookContentProvider.USER_CONTENT_URI;

        Log.e("zeal", "--------------------add--------------");

        ContentValues values = new ContentValues();
        values.put("_id", 6);
        values.put("name", "JAVA编程思想");
        getContentResolver().insert(bookUri, values);


        Log.e("zeal", "--------------------query--------------");

        //查询图书
        Cursor bookCursor = getContentResolver().query(bookUri, new String[]{"_id", "name"}, null, null, null);

        while (bookCursor.moveToNext()) {
            int bookId = bookCursor.getInt(0);
            String bookName = bookCursor.getString(1);

            Book book = new Book(bookId, bookName);

            Log.e("zeal", book.toString());

        }
        bookCursor.close();

        Log.e("zeal", "--------------------update--------------");

        ContentValues updateValues = new ContentValues();
        updateValues.put("name", "JAVA编程思想第四版");
        String where = "_id = ?";
        String[] selectionArgs = {"6"};
        getContentResolver().update(bookUri, updateValues, where, selectionArgs);

        Log.e("zeal", "--------------------query--------------");
        //查询图书
        Cursor bookCursor2 = getContentResolver().query(bookUri, new String[]{"_id", "name"}, null, null, null);

        while (bookCursor2.moveToNext()) {
            int bookId = bookCursor2.getInt(0);
            String bookName = bookCursor2.getString(1);

            Book book = new Book(bookId, bookName);

            Log.e("zeal", book.toString());

        }
        bookCursor2.close();

        Log.e("zeal", "--------------------delete--------------");

        where = "_id = ?";
        selectionArgs = new String[]{"6"};
        getContentResolver().delete(bookUri, where, selectionArgs);


        //查询图书
        Cursor bookCursor3 = getContentResolver().query(bookUri, new String[]{"_id", "name"}, null, null, null);

        while (bookCursor3.moveToNext()) {
            int bookId = bookCursor3.getInt(0);
            String bookName = bookCursor3.getString(1);

            Book book = new Book(bookId, bookName);

            Log.e("zeal", book.toString());

        }
        bookCursor3.close();


        //查询图书
        Cursor userCursor = getContentResolver().query(userUri, new String[]{"_id", "name", "sex"}, null, null, null);

        while (userCursor.moveToNext()) {
            int userId = userCursor.getInt(0);
            String userName = userCursor.getString(1);
            boolean userSex = userCursor.getInt(2) == 1;

            Log.e("zeal", "userId=" + userId + " userName=" + userName + " userSex=" + userSex);

        }
        userCursor.close();

//        getContentResolver().unregisterContentObserver(new ContentObserver() {
//            @Override
//            public boolean deliverSelfNotifications() {
//                return super.deliverSelfNotifications();
//            }
//
//            @Override
//            public void onChange(boolean selfChange) {
//                super.onChange(selfChange);
//            }
//
//            @Override
//            public void onChange(boolean selfChange, Uri uri) {
//                super.onChange(selfChange, uri);
//            }
//        });


    }
}
