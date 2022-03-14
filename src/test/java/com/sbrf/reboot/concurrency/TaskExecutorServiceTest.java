package com.sbrf.reboot.concurrency;

import com.sbrf.reboot.service.concurrency.Task;
import com.sbrf.reboot.service.concurrency.TaskExecutorService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


public class TaskExecutorServiceTest {

    @Test
    public void successRunMultithreading() throws InterruptedException {

        Task task = Mockito.mock(Task.class);
        CountDownLatch latch = new CountDownLatch(2);

        TaskExecutorService taskExecutorService = new TaskExecutorService(2);

        doAnswer((e -> {
            latch.countDown();
            return null;
        })).when(task).run();

        taskExecutorService.execute(task);

        latch.await();

        assertEquals(0, latch.getCount());
        verify(task, times(2)).run();

        taskExecutorService.shutdown();
    }

    private void runMultithreading(TaskExecutorService taskExecutorService, int numOfThreads) throws InterruptedException {
        Task task = Mockito.mock(Task.class);

        CountDownLatch latch = new CountDownLatch(numOfThreads);
        taskExecutorService.setNumberOfThreads(numOfThreads);

        doAnswer((e -> {
            latch.countDown();
            return null;
        })).when(task).run();

        taskExecutorService.execute(task);

        latch.await();

        assertEquals(0, latch.getCount());
        verify(task, times(numOfThreads)).run();
    }

    @Test
    public void changeThreadsNumber() throws InterruptedException {

        TaskExecutorService taskExecutorService = new TaskExecutorService(2);

        List<Integer> numOfThreadsForTests = Arrays.asList(1, 2, 3, 4, 5, 6);

        for(int numOfThreads: numOfThreadsForTests){
            runMultithreading(taskExecutorService, numOfThreads);
        }

        taskExecutorService.shutdown();
    }
}