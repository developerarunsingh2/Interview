package com.practice.May_June_26;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Multithreading {
    public static void main(String[] args) {

        //Restaurant:  meal
        Meal m1 = new Meal("idli",60);
        Meal m2 = new Meal("dosa",80);

        Customer c1 = new Customer("Ram","1",123,false);
        Customer c2 = new Customer("Shyam","2",456,false);

        Runnable r1= ()-> c1.setMealTaken(true);
        Runnable r2 = ()-> c2.setMealTaken(true);

        ExecutorService executorService = java.util.concurrent.Executors.newFixedThreadPool(10);
        Future<?> submit1 = executorService.submit(r1);
        Future<?> submit2 = executorService.submit(r2);
        try {
            System.out.println("[Main Thread] Submitting orders to the kitchen pool...");

            // Create tasks
            ServeOrderTask task1 = new ServeOrderTask(c1, m1);
            ServeOrderTask task2 = new ServeOrderTask(c2, m2);

            // Submit tasks asynchronously
            Future<String> receipt1 = executorService.submit(task1);
            Future<String> receipt2 = executorService.submit(task2);

            System.out.println("[Main Thread] Orders submitted. Doing other checkout tasks while cooking...");

            // Process Receipt 1 (Ram's Order)
            try {
                String result1 = receipt1.get(); // Blocks until task 1 finishes
                System.out.println("[Receipt Response] " + result1);
            } catch (Exception e) {
                System.err.println("[Receipt Error Handled] Ram's order crashed: " + e.getCause().getMessage());
            }

            // Process Receipt 2 (Shyam's Order)
            try {
                String result2 = receipt2.get(); // Blocks until task 2 finishes
                System.out.println("[Receipt Response] " + result2);
            } catch (Exception e) {
                System.err.println("[Receipt Error Handled] Shyam's order crashed: " + e.getCause().getMessage());
            }

        } finally {
            // Graceful shutdown sequence
            System.out.println("[Main Thread] Closing the kitchen pool...");
            executorService.shutdown();
            try {
                // Wait up to 5 seconds for existing threads to terminate naturally
                if (!executorService.awaitTermination(5, TimeUnit.SECONDS)) {
                    executorService.shutdownNow(); // Force kill if stuck
                }
            } catch (InterruptedException e) {
                executorService.shutdownNow();
            }
            System.out.println("[Main Thread] Program finished cleanly.");
        }
    }
}
