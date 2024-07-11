package com.ps.task;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public interface TaskExecutor {
    
    <T> Future<T> submitTask(Task<T> task);

    void awaitTerminationAndShutdownHook(int timeout, TimeUnit unit);
}