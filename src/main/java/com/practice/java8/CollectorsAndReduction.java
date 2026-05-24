package com.practice.java8;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CollectorsAndReduction {
    public static void main(String[] args) {

        /*
        * Convert a List of Strings to a single comma-separated String using Collectors.joining
        * */
        List<String> fruitsList = List.of("Apple","banana", "Cherry","Apricot","Blueberry");
        String str = fruitsList.stream().collect(Collectors.joining(","));
        System.out.println("comma separated: "+str);

        /*
        * Group elements by a property using Collectors.groupingBy()
        * */

        Map<Character,List<String>> grouped = fruitsList.stream().collect(
                Collectors.groupingBy(s->s.charAt(0))
        );
        grouped.forEach((key,value)-> System.out.println(key + "->"+value));
        System.out.println(grouped);

        /*
        * Partition elements into two groups based on a predicate using Collectors.partitioningBy()
        * */

        Predicate<String> isOdd = fruits->fruits.length()%2==0;
        Map<Boolean, List<String>> map = fruitsList.stream().collect(Collectors.partitioningBy(isOdd));
        System.out.println(map);
        System.out.println("print fruits with even length: " +map.get(true));
        System.out.println("print fruits with odd length: " +map.get(false));

        /*
        * Count elements using Collectors.counting
        * */

        long count = fruitsList.stream().count();
        System.out.println("fruitlist count: "+count);

        //
        System.out.println("count with Collectors.counting: " + fruitsList.stream().collect(Collectors.counting()));
    /*
    * Find max/min element using Collectors.maxBy() and Collectors.minBy()
    * */

        Optional<String> max = fruitsList.stream().collect(Collectors.maxBy(Comparator.naturalOrder()));
        Optional<String> min = fruitsList.stream().collect(Collectors.minBy(Comparator.naturalOrder()));

        max.ifPresent(s-> System.out.println("max: "+s));
        min.ifPresent(s-> System.out.println("min: "+s));

        /*
        *
        * Calculate the sum of integers using Collectors.summingInt
        * */

        List<Integer> integers =  Arrays.asList(1,2,3,4,5,6);
        int sum = integers.stream().collect(Collectors.summingInt(Integer::intValue));
        System.out.println("Sum of numbers: "+sum);



    }
}
