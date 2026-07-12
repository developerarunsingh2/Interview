package com.practice.May_June_26;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

class ServeOrderTask implements Callable<String> {
    private final Customer customer;
    private final Meal meal;

    public ServeOrderTask(Customer customer, Meal meal) {
        this.customer = customer;
        this.meal = meal;
    }

    @Override
    public String call() throws Exception {
        String threadName = Thread.currentThread().getName();
        System.out.println("[" + threadName + "] Starting to prepare " + meal.getName() + " for " + customer.getName());

        // Simulate Inventory Error
        if (meal.getName().equalsIgnoreCase("idli")) {
            throw new OutOfStockException("Kitchen Error: Out of Idli batter!");
        }

        // Simulate cooking time (Network/DB delay)
        TimeUnit.SECONDS.sleep(2); 

        // Update state
        customer.setMealTaken(true);
        
        // Return confirmation receipt
        return "SUCCESS: Served " + meal.getName() + " to " + customer.getName() + " at Table " + customer.getTableNo();
    }
}