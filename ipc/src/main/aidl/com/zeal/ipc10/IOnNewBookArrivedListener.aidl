// IOnNewBookArrivedListener.aidl
package com.zeal.ipc10;
import com.zeal.ipc10.Book;
// Declare any non-default types here with import statements

interface IOnNewBookArrivedListener {
    void onNewBookArrived(in Book newBook);
}
