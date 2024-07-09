package com.hnt;

import java.util.*;
import java.util.function.Supplier;
import java.util.function.BiConsumer;

public class StreamCollectExample {
    public static void main(String[] args) {
     //   List<String> names = List.of("Alice", "Bob", "Charlie", "David");
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David", "Emily");
        // Using collect with a custom implementation
        Object result = names.stream()
                .collect(
                        // Supplier: Create an ArrayList to store the results
                        (Supplier<Object>) ArrayList::new,

                        // BiConsumer: Accumulator - Add each element to the ArrayList
                        (list, name) -> ((ArrayList<Object>) list).add(name.toUpperCase()),


                        // BiConsumer: Combiner - Merge two ArrayLists into one
                        (list1, list2) -> ((ArrayList<Object>) list1).addAll((Collection<?>) list2)
                );

//        Optional.ofNullable(names)
//                .stream()
//                        .filter(Objects::nonNull)
//
        // Display the result
        System.out.println(result);
    }
}