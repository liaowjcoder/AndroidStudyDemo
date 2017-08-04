package com.zeal.designpatter;

/**
 * Created by zeal on 2017/7/10.
 */

public abstract class Singleton<T> {

    private T mInstance;


    protected abstract T create();

    public final T get() {
        synchronized (this) {
            if (mInstance == null) {
                mInstance = create();
            }
            return mInstance;
        }
    }
}
