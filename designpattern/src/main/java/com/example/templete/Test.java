package com.example.templete;

/**
 * Created by zeal on 2017/8/7.
 */

public class Test {

    public static void main(String[] args) {

        BossWork bossWork = new BossWork();
        StaffWork staffWork = new StaffWork();

        bossWork.newDay();

        System.out.println("-------");

        staffWork.newDay();

    }
}
