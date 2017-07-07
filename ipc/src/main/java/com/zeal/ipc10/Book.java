package com.zeal.ipc10;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by liaowj on 2017/7/5.
 */

public class Book implements Parcelable {
    public int bookid;
    public String bookname;

    public Book(int bookid, String bookname) {
        this.bookid = bookid;
        this.bookname = bookname;
    }

    protected Book(Parcel in) {

        bookid = in.readInt();
        bookname = in.readString();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(bookid);
        dest.writeString(bookname);
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookid=" + bookid +
                ", bookname='" + bookname + '\'' +
                '}';
    }
}
