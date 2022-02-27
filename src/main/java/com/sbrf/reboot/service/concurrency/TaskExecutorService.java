package com.sbrf.reboot.service.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class TaskExecutorService {

    private int numberOfThreads;

    private final ExecutorService service;

    public TaskExecutorService(int numberOfThreads) {
        this.service = Executors.newFixedThreadPool(numberOfThreads);
        this.numberOfThreads = numberOfThreads;
    }

    public void setNumberOfThreads(int numberOfThreads) {
        ThreadPoolExecutor executor = (ThreadPoolExecutor)this.service;
        executor.setCorePoolSize(Math.max(numberOfThreads, 0));
        executor.setMaximumPoolSize(Math.max(numberOfThreads, 1));
        this.numberOfThreads = numberOfThreads;
    }

    public void execute(Task task) {
        for (int i = 0; i < numberOfThreads; i++)
            service.execute(task);
    }

    public void shutdown() {
        service.shutdown();
    }
}
