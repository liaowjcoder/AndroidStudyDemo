package com.example.strategy;

/**
 * Created by zeal on 2017/8/7.
 */

public class Context {

    private AbstractStrategy strategy;

    public void setStrategy(AbstractStrategy strategy) {
        this.strategy = strategy;
    }

}
