package com.practice.java8;

import org.w3c.dom.ls.LSOutput;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

public class ParallelStreamAndConcurrency {
    public static void main(String[] args) {

        /*
        * Difference between .stream() and .parallelStream() uses multiple threads for parallel processing.
        *
        * Ans: .stream() is sequencial , processes elements one by one
        * whereas parallelStream() uses multiple threads for parallel processing
        * */

        List<String> fruitsList = List.of("Apple","banana", "Cherry","Apricot","blueberry");
        fruitsList.stream().forEach(System.out::println);
        fruitsList.parallelStream().forEach(System.out::println);

        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7);
        System.out.println("Sequencial stream");
        numbers.stream().forEach(n-> System.out.println(
                Thread.currentThread().getName() + " processed " +n
        ));

        System.out.println("Parallel stream");

        numbers.parallelStream().forEach(
                n->
                        System.out.println(
                                Thread.currentThread().getName() + " Processed "+n
                        )
        );


        /*
        * Safely accumulate elements in parallel using Collectors.toConcurrentMap()
        * */

        ConcurrentMap<Character, String> fruitsWithFirstChar = fruitsList.parallelStream().
                collect(Collectors.toConcurrentMap(
                                s->s.charAt(0),
                        s->s,
                        (existing,replacement)-> existing + "|"+replacement

                ));

        fruitsWithFirstChar.forEach((key,value)->
                        System.out.println(key+" -> "+value)
                );

        /*
        * perform reduction using .reduce
        *
        * combines elements of string using an accumulator
        * */

        List<Integer>intList = Arrays.asList(1,2,3,4,5,6);
       Optional<Integer> sum = intList.stream().reduce(
               Integer::sum
       );
        sum.ifPresent(result ->System.out.println("sum of all numbers in int list: " +result));
    }
}
