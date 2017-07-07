// IBookManager.aidl
package com.zeal.ipc10;

// Declare any non-default types here with import statements
import com.zeal.ipc10.Book;
import com.zeal.ipc10.IOnNewBookArrivedListener;
interface IBookManager {

    //注意需要使用 in 描述
    void addBook(in Book book);

    List<Book> getBookList();

    void registerNewBookListener(IOnNewBookArrivedListener listener) ;
    void unregisterNewBookListener(IOnNewBookArrivedListener listener) ;
}
