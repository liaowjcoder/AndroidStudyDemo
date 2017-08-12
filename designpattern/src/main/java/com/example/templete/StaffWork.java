package com.example.templete;

/**
 * Created by zeal on 2017/8/7.
 */

public class StaffWork extends AbstractWork {
    @Override
    protected void goToWork() {
        System.out.println("员工坐公交车去上班");
    }

    @Override
    protected void work() {
        System.out.println("做老板分配的任务");
    }

    @Override
    protected void offwork() {
        System.out.println("员工做公交车下班回家");
    }
}
