package com.hnt;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolExample {

    public static void main(String[] args) {
        // Create a thread pool with a fixed number of threads
        ExecutorService executor = Executors.newFixedThreadPool(4);

        // Submit tasks to the thread pool
        for (int i = 0; i < 10; i++) {
            final int taskId = i;
            executor.submit(() -> {
                System.out.println("Task " + taskId + " started");
                // Simulate some computation or I/O operation
                long result = calculateResult();
                System.out.println("Task " + taskId + " completed with result " + result);
            });
        }

        // Shutdown the thread pool when all tasks are completed
        executor.shutdown();
    }

    private static long calculateResult() {
        // Simulate some computation
        long result = 0;
        for (long i = 0; i < 10000000; i++) {
            result += i * i;
        }
        return result;
    }
}
