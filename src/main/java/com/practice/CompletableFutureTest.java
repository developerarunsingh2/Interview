package com.practice;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

public class CompletableFutureTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Employee> employeeCompletableFuture   = new CompletableFuture<>();
        CompletableFuture<Employee> employeeCompletableFuture1   = new CompletableFuture<>();
        Supplier<Employee> s= () -> {
                System.out.println("Arun");
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return new Employee(1,"Arun",new Department(1,"IT"),123);
        };
        System.out.println("Main thread part 1");
         employeeCompletableFuture.supplyAsync(s)
                 .thenApply((e)-> e.getSalary()*2)
                 .thenAccept(result-> System.out.println(
                 "Salary of Arun is " + result + System.nanoTime()))
                 .thenCompose(
                         n->{
                             System.out.println(s.get());
                          return   CompletableFuture.supplyAsync(s);
                         }
                 )
        ;

        System.out.println("Main thread part 2 " + System.nanoTime());
        employeeCompletableFuture.join();

        /*get: throws checked exception( put in try catch block)
          vs
        /join: unchecked precise code */

        /* theyApply(): works as mapping or modulating same call
        vs
        thenCompose(): better for dependent separate task with another computable future. for eg: orderservice and
        then call customer service with thenCompose

        * */
        /*Asynchronous logic means parallel to main thread these computations will happen in computable future
        *
        * */
    }
}
