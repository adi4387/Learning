package com.adruy.thread;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        for (int i = 0; i < 100; i++) {
            executorService.execute(new NumberPrinter(i));
        }
    }
}

class NumberPrinter implements Runnable {

    private int number;

    public NumberPrinter(final int number) {
        this.number = number;
    }

    @Override
    public void run() {
        System.out.println(number + " " + Thread.currentThread().getName());
    }
}
