package com.ps.action;

import java.util.concurrent.Callable;

public record WriteStringAction() implements Callable<String> {

    @Override
    public String call() throws Exception {
        System.out.println(Thread.currentThread().getName() + " has started writing");
        Thread.sleep(1000);
        System.out.println(Thread.currentThread().getName() + " has finised writing");
        return "written String";
    }
}