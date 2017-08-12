package com.example.templete;

/**
 * Created by zeal on 2017/8/7.
 */

public class BossWork extends AbstractWork {
    @Override
    protected void goToWork() {
        System.out.println("老板开车去上班...");
    }

    @Override
    protected void work() {
        System.out.println("分配工作给员工");
    }

    @Override
    protected void offwork() {
        System.out.println("开车下班回家");
    }
}
