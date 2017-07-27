package com.example;

/**
 * Created by liaowj on 2017/7/27.
 * 实现 Comparable<Person> 接口，提供 compareTo 比较两个对象的大小关系
 */

public class User  {

    public String name;
    public int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }


    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

}
