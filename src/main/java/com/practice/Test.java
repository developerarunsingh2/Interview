package com.practice;

import java.util.Comparator;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
public class Test {
    public static void main(String[] args) throws Exception {
        LambdaTest lambdaValue = (int a, int b)->(a+b);
        System.out.println(lambdaValue.functionalMethodToAdd(10,20));

        //function
        Function<String,Integer> functionValue = integer -> integer.length()+10;
        System.out.println(functionValue.apply("hello"));
        //further lookinto andThen, compose(),

        Predicate<Integer> isOdd = n->n%2==1;
        System.out.println("its odd number: " + isOdd.test( 31));
        //further look into and, negate, or, isEqual, not

        Consumer<Integer> consumer= (input)-> System.out.println("input taken:"+input);
        consumer.accept(10);

        Supplier<Integer>supplier = ()->10+20;
        System.out.println(supplier.get());

        UnaryOperator<Integer> operator = integer -> {
          //  System.out.println("operator input; "+integer);
            return integer;
        };
        System.out.println(operator.apply(10));

        Runnable runnable = ()->
        System.out.println("Runnable is running");
       runnable.run();

        //Callable input = null;
        Callable<String> callable = () -> {return "callable is running";};
     //   System.out.println(callable.call());

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> future = executorService.submit(callable);
        System.out.println(future.get());
        executorService.shutdown();

//Completablefuture
        Comparator<Integer>comparator = (a,b)->a.compareTo(b);
        System.out.println("Comparator: Integer compared values: " +comparator.compare(10,10));

        Comparable<Integer>comparable =(a) ->a.compareTo(20);
        System.out.println("Comparable Integer compare values: "+comparable.compareTo(10));

        LambdaTest.staticMethod();




    }
}
