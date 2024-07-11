package com.ps.task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class TaskExecutorService implements TaskExecutor {

    private final TaskGroup taskGroup;
    private final ExecutorService executorService;

    public TaskExecutorService(final int concurrency, final TaskGroup taskGroup) {
        this.taskGroup = taskGroup;
        this.executorService = Executors.newFixedThreadPool(concurrency);
    }

    @Override
    public <T> Future<T> submitTask(Task<T> task) {
        return executorService.submit(task.taskAction());
    }

    public void awaitTerminationAndShutdownHook(int timeout, TimeUnit unit) {
        System.out.println("Shutting down : " + taskGroup);
        executorService.shutdown();
        try {
            if(!executorService.awaitTermination(timeout, unit)) {
                System.out.println("Force shutting down : "+ taskGroup);
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            System.out.println("Force shutting down after exception : "+ taskGroup);
            executorService.shutdownNow();
        }
    }
}
