// IBookManager.aidl
package com.zeal.ipc9;

// Declare any non-default types here with import statements
import com.zeal.ipc9.Book;
import com.zeal.ipc9.IOnNewBookArrivedListener;
interface IBookManager {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
//    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
//            double aDouble, String aString);

    //注意需要使用 in 描述
    void addBook(in Book book);

    List<Book> getBookList();

    void registerNewBookListener(IOnNewBookArrivedListener listener) ;
    void unregisterNewBookListener(IOnNewBookArrivedListener listener) ;
}
