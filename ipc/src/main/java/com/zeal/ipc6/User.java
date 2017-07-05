package com.zeal.ipc6;

import java.io.Serializable;

/**
 * Created by liaowj on 2017/7/5.
 */

public class User implements Serializable{



    public String name;
    public int age;
    public boolean isMale;

    public User(String name, int age, boolean isMale) {
        this.name = name;
        this.age = age;
        this.isMale = isMale;
    }

}
