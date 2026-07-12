package com.practice.May_June_26;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class CollectToMapExample {
    public static void main(String[] args) {
        // 1. Create the original messy map
        Map<String, Integer> studentScores = new HashMap<>();
        studentScores.put("Charlie", 85);
        studentScores.put("Alice", 95);
        studentScores.put("Bob", 90);

        System.out.println("Original Map: " + studentScores);

        // 2. Sort by key and collect into a LinkedHashMap
        Map<String, Integer> sortedMap = studentScores.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,               // Key mapper: keep the original key
                        Map.Entry::getValue,             // Value mapper: keep the original value
                        (oldValue, newValue) -> oldValue, // Merge function: what to do if keys duplicate
                        LinkedHashMap::new               // Map factory: Force it to be a LinkedHashMap!
                ));

        // 3. Print the new sorted map object
        System.out.println("Sorted Map:   " + sortedMap);
        
        // Prove that it is a LinkedHashMap instance
        System.out.println("Map Type:     " + sortedMap.getClass().getName());
    }
}