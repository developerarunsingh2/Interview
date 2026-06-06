package com.hnt;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class Interview {
    public static void main(String args[]){
//        System.out.println("hello");
//
//        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
//        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");
//
//      names.stream().collect(Collectors.toSet());
//        System.out.println("hello how are you");
//        Scanner sc = new Scanner(System.in);
//        sc.nextInt();
//
//        ConcurrentHashMap<String, Integer>score = new ConcurrentHashMap<>();
//        score.put("Ram",1);
//        score.put("Shiva",2);

        String name = "ArunKumar";


        //synchron interview
        //remove duplicates
        //
       // for(char c: name.)

        Set<Character> set = new LinkedHashSet<>();
        for(int i=0;i<name.length();i++)
        {
            set.add(name.charAt(i));
        }

        String filtered = "";
        for(char c: set)
        {
            filtered = filtered + c;
        }

      //  name.chars().mapToObj((char)c)
        System.out.println("duplicate removed: " +filtered);
    }




}
