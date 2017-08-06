package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

/*
Comparable<T>
惟一一个接口方法：public int compareTo(T o);
将当前对象和其他对象o进行比较，返回一个整形的数据
    >0:当前对象比o大
    =0:当前对象和o一样大
    <0:当前对象比o小

Comparator<T>
int compare(T o1, T o2);

两种比较器的区别？
Comparable 是在类内部就进行比较的，而Comparator实在外部进行对象的比较的。
当设计某一个类时没有考虑到对象的比较问题时，就可以使用 Comparator ，它可以在外部
去实现比较。

 */
public class Test {

    public static void main(String[] args) {


        //Comparable的使用
        Person person1 = new Person("jack", 1);
        Person person2 = new Person("android", 2);
        Person person3 = new Person("cutter", 10);
        Person person4 = new Person("jack", 12);

        Person[] persons = new Person[]{person1, person2, person3, person4};

        //比较两个对象的顺序
        int i = person2.compareTo(person3);
        System.out.println(i);
        //排序（数组）
        Arrays.sort(persons);

        for (Person person : persons) {
            System.out.println(person);

        }

        System.out.println("-----------------------------");

        //Comparator的使用
        User user1 = new User("jack", 1);
        User user2 = new User("android", 2);
        User user3 = new User("cutter", 10);
        User user4 = new User("jack", 12);

        TreeSet<User> treeSet = new TreeSet<User>(new MyComparator());
        treeSet.add(user1);
        treeSet.add(user2);
        treeSet.add(user3);
        treeSet.add(user4);

        Iterator<User> iterator = treeSet.iterator();

        while (iterator.hasNext()) {
            User next = iterator.next();
            System.out.println(next);
        }
    }

    private static class MyComparator implements Comparator<User> {

        @Override
        public int compare(User user, User user2) {
            int compareNmae = user.name.compareTo(user2.name);
            if (compareNmae == 0) {
                if (user.age > user2.age) {
                    return 1;
                } else if (user.age < user2.age) {
                    return -11;
                } else {
                    return 0;
                }
            }
            return compareNmae;
        }
    }
}
