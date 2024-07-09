package com.hnt;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Interview {
    public static void main(String args[]){
        System.out.println("hello");

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");

      names.stream().collect(Collectors.toSet());
    }
}
