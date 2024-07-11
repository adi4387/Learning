package com.ps;

import static com.ps.task.TaskType.READ;
import static com.ps.task.TaskType.WRITE;
import static java.util.UUID.randomUUID;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import com.ps.action.ReadStringAction;
import com.ps.action.WriteStringAction;
import com.ps.task.Task;
import com.ps.task.TaskExecutor;
import com.ps.task.TaskExecutorService;
import com.ps.task.TaskGroup;

public class Main {

    public static void main(String[] args) throws Exception {

        TaskGroup taskGroupA = new TaskGroup(randomUUID());
        TaskGroup taskGroupB = new TaskGroup(randomUUID());

        Callable<String> strReader1 = new ReadStringAction();
        Task<String> readStringTask1 = new Task<>(randomUUID(), taskGroupA, READ, strReader1);

        Callable<String> strReader2 = new ReadStringAction();
        Task<String> readStringTask2 = new Task<>(randomUUID(), taskGroupB, READ, strReader2);
        
        Callable<String> strReader3 = new ReadStringAction();
        Task<String> readStringTask3 = new Task<>(randomUUID(), taskGroupA, READ, strReader3);

        Callable<String> strReader4 = new ReadStringAction();
        Task<String> readStringTask4 = new Task<>(randomUUID(), taskGroupB, READ, strReader4);

        Callable<String> strWriter1 = new WriteStringAction();
        Task<String> writeStringTask1 = new Task<>(randomUUID(), taskGroupA, WRITE, strWriter1);

        Callable<String> strWriter2 = new WriteStringAction();
        Task<String> writeStringTask2 = new Task<>(randomUUID(), taskGroupB, WRITE, strWriter2);

        Callable<String> strWriter3 = new WriteStringAction();
        Task<String> writeStringTask3 = new Task<>(randomUUID(), taskGroupA, WRITE, strWriter3);

        Callable<String> strWriter4 = new WriteStringAction();
        Task<String> writeStringTask4 = new Task<>(randomUUID(), taskGroupB, WRITE, strWriter4);

        TaskExecutor threadGroup1TaskExecutor = new TaskExecutorService(2, taskGroupA);
        TaskExecutor threadGroup2TaskExecutor = new TaskExecutorService(2, taskGroupB);


        if(threadGroup1TaskExecutor.submitTask(readStringTask1).isDone()) {
            System.out.println(readStringTask1 + " finished with output " + threadGroup1TaskExecutor.submitTask(readStringTask1).get());
        }

        if(threadGroup2TaskExecutor.submitTask(readStringTask2).isDone()) {
            System.out.println(readStringTask2 + " finished with output " + threadGroup1TaskExecutor.submitTask(readStringTask2).get());
        }

        if(threadGroup1TaskExecutor.submitTask(readStringTask1).isDone()) {
            
        }

        if(threadGroup1TaskExecutor.submitTask(readStringTask1).isDone()) {
            
        }

        if(threadGroup1TaskExecutor.submitTask(readStringTask1).isDone()) {
            
        }

        if(threadGroup1TaskExecutor.submitTask(readStringTask1).isDone()) {
            
        }

        if(threadGroup1TaskExecutor.submitTask(readStringTask1).isDone()) {
            
        }

        if(threadGroup1TaskExecutor.submitTask(readStringTask1).isDone()) {
            
        }
        System.out.println(writeStringTask1 + " finished with output " + threadGroup1TaskExecutor.submitTask(writeStringTask1).get());
        System.out.println(writeStringTask2 + " finished with output " + threadGroup1TaskExecutor.submitTask(writeStringTask2).get());
        System.out.println(readStringTask3 + " finished with output " + threadGroup2TaskExecutor.submitTask(readStringTask3).get());
        System.out.println(readStringTask4 + " finished with output " + threadGroup2TaskExecutor.submitTask(readStringTask4).get());
        System.out.println(writeStringTask3 + " finished with output " + threadGroup2TaskExecutor.submitTask(writeStringTask3).get());
        System.out.println(writeStringTask4 + " finished with output " + threadGroup2TaskExecutor.submitTask(writeStringTask4).get());
        threadGroup1TaskExecutor.awaitTerminationAndShutdownHook(1000, TimeUnit.MILLISECONDS);
        threadGroup2TaskExecutor.awaitTerminationAndShutdownHook(1000, TimeUnit.MILLISECONDS);
    }
}