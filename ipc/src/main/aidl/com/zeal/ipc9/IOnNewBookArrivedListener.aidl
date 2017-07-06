// IOnNewBookArrivedListener.aidl
package com.zeal.ipc9;
import com.zeal.ipc9.Book;
// Declare any non-default types here with import statements

interface IOnNewBookArrivedListener {
    void onNewBookArrived(in Book newBook);
}
