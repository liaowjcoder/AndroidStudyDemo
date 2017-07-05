// BookManager.aidl
package com.zeal.ipc;

// Declare any non-default types here with import statements
import com.zeal.ipc.Book;

interface BookManager {
    ArrayList<Book> getBookList();

    void addBook(Book book);
}
