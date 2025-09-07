package com.practice.java8;

import com.practice.LambdaTest;

import java.util.List;
import java.util.Random;
import java.util.function.*;

public class LambdasAndFunctionalInterfaces {
    public static void main(String[] args) {
        /*
        Q1: Create a custom functional interface and use it
         */

        LambdaTest lambdaValue = (int a, int b)->(a+b);
        System.out.println(lambdaValue.functionalMethodToAdd(10,20));

        /*
        * Q2: Demonstrate method reference for static, instance, and constructor
        *
        * Ans:
        *
        * Static method ref:
        *  ClassName:: static Method
        *
        * Instance Method ref:
        * istance::method
        *
        * Constructor reference:
        * ClassName::new
        *
        * */

        Function<String,String> staticRef = Utility::staticMethod;
        System.out.println(staticRef.apply("Static Method"));
        Utility utility = new Utility();
        Function<String,String> instanceRef = utility::instanceMethod;
        System.out.println(instanceRef.apply("Instance method"));


        // Constructor reference
        Supplier<StringBuilder> constructorRef = StringBuilder::new;
        StringBuilder sb = constructorRef.get();
        sb.append("Constructor Ref");
        System.out.println(sb.toString());

        /*
        * Q3: Compose two functions using Function.compose() and Function.andThen()
        *
        * f1.compose(f2) means f1(f2(x))
        * f1.andThen(f2) means f2(f1(x))
        *
        * */

        List<String> electronics = List.of("Iron","Laptop","Charger");
        Function<String,String> function = Utility::staticMethod;
        function.compose(function).andThen(function);
        System.out.println(function.apply("hello"));

        Function<Integer,Integer> multiplyBy2 = x->x*2;
        Function<Integer,Integer> add10 = x->x+10;

        //Compose: add10(multiplyBy2(x))

        Function<Integer,Integer> composed = add10.compose(multiplyBy2);
        System.out.println("Compose Result: "+composed.apply(5));

        //Andthen:multiplyBy2(add10(x))

        Function<Integer,Integer> andThen = add10.andThen(multiplyBy2);
        System.out.println("AndThen Result: "+andThen.apply(5));

        /*
        * Use Predicate chaining(and,or,negate)
        * */

        Predicate<Integer> isEven = x->x%2==0;
        Predicate<Integer> isGreaterThan10 = x-> x>10;

        System.out.println("isEven and  > 10 ? "+isEven.and(isGreaterThan10).test(12));
        System.out.println("isEven OR >10 ?"+isEven.or(isGreaterThan10).test(7));
        System.out.println("is not even? "+isEven.negate().test(7));

        /*
        * Implement Supplier functional random numbers
        * */

        Supplier<Integer> randomSupplier = ()->new Random().nextInt(100);
        System.out.println("Random Number: "+ randomSupplier.get());

        /*
        * Implement Consumer functional interface to print a list of strings
        * */

        List<String> stringList = List.of("helo", "radhe","ram","siya");
        Consumer<List<String>> consumer = x-> System.out.println(x);
        consumer.accept(stringList);

        /*Implement BiFunction to concatenate two strings with a separator */

        BiFunction<String,String, String> concatenationWithDash = (a,b) ->a+"-"+b;

        String result = concatenationWithDash.apply("Hello","World");
        System.out.println(result);
    }
}
