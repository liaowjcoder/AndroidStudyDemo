package com.example;

/**
 * Created by liaowj on 2017/7/27.
 * 实现 Comparable<Person> 接口，提供 compareTo 比较两个对象的大小关系
 */

public class Person implements Comparable<Person> {

    public String name;
    public int age;

    public Person(String name, int age) {
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

    @Override
    public int compareTo(Person person) {

        int compareNmae = this.name.compareTo(person.name);
        if (compareNmae == 0) {
            if (this.age > person.age) {
                return 1;
            } else if (this.age < person.age) {
                return -1;
            } else {
                return 0;
            }
        }
        return compareNmae;
    }
}
