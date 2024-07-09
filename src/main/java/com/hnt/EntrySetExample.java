package com.hnt;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class EntrySetExample {
    public static void main(String[] args) {
        // Create a HashMap
        Map<String, Integer> map = new HashMap<>();

        // Add some key-value pairs to the map
        map.put("Apple", 10);
        map.put("Banana", 20);
        map.put("Orange", 15);

        // Get the set view of the mappings in the map
        Set<Map.Entry<String, Integer>> entrySet = map.entrySet();

        // Iterate over the entry set and print each key-value pair
        for (Map.Entry<String, Integer> entry : entrySet) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println(key + " : " + value);
        }
    }
}
