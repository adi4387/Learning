package com.ps.action;

import java.util.concurrent.Callable;

public record ReadStringAction() implements Callable<String> {

    @Override
    public String call() throws Exception {
        System.out.println(Thread.currentThread().getName() + " has started reading");
        Thread.sleep(1000);
        System.out.println(Thread.currentThread().getName() + " has finised reading");
        return "read String";
    }
}