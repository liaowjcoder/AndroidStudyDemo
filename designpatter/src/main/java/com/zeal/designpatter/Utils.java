package com.zeal.designpatter;


/**
 * Created by zeal on 2017/7/10.
 */

public class Utils {

    static public Utils getDefault() {

        return gDefault.get();
    }

    private static final Singleton<Utils> gDefault = new Singleton<Utils>() {
        protected Utils create() {
            return new Utils();
        }
    };
}
