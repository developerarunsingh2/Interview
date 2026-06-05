package com.practice.May_June_26;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class P_05_06 {
    static void main() {

        String test = "duplicatecharacter";
        //approach 1 by using set
        Set<Character> set = new HashSet<>();
        for(int i=0;i<test.length();i++)
        {
            if(!set.add(test.charAt(i)))
            {
                System.out.println("duplicate character is : "+test.charAt(i));
            }
        }

        //approach 2 by using java 8 still set
        set.clear();
       Character result = test.chars().mapToObj(c->(char)c).filter(s->!set.add(s))
               .findFirst().get();
        System.out.println("duplicate character with java 8 stream with set: "+result);

        //approach 3
        // entry set
        Character output = test.chars().mapToObj(c->(char)c)
                .collect(
                        Collectors.groupingBy(
                                Function.identity(),
                                LinkedHashMap::new,
                                Collectors.counting()
                        )
                ).entrySet()
                .stream()
                .filter(p->p.getValue()>1)

                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);

        System.out.println("duplicate character check with LinkedHashMap purely with java 8 streams: "+output);

        Map<Character,Integer> map = new LinkedHashMap<>();
        map.put('r',1);
        map.put('a',2);
        map.put('m',3);

       // Optional<Map.Entry<Character, Integer>> first =
                map.entrySet()

                        .stream().forEach(e-> System.out.println(
                        "get Value : " +
                e.getValue()+" get Key: "+e.getKey()
        ));

//        System.out.println("Entry set first element is: "+first.get());


        map.entrySet().stream().filter(e->e.getValue()==3)
                .findFirst().map(m->m.setValue(8));

map.entrySet().stream().filter(m->m.getValue()==2).findFirst()
        .map(m->{
            Integer input = m.setValue(100);
            System.out.println("old value for a is: "+input);
            return input;
        });


       System.out.println("current value of m is:" +map.get('a'));
        System.out.println(map);

        //equals method usecase

        Map<Character,Integer> compareMap = new LinkedHashMap<>();
        compareMap.put('r',1);
        compareMap.put('a',100);
        compareMap.put('m',8);
        Set<Map.Entry<Character, Integer>> mapEntrySet = map.entrySet();
        Set<Map.Entry<Character,Integer>>compareMapSet = compareMap.entrySet();
        System.out.println(map.equals(compareMap));// expected output false because we are comparing object
        System.out.println(mapEntrySet.equals(compareMapSet));
        // in which case they will be equal? please implement

        System.out.println(map.hashCode());
        System.out.println(compareMap.hashCode());

        //comparing by values

        mapEntrySet.stream().sorted(Map.Entry.comparingByKey()).collect();



    }
}
