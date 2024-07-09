package com.ey;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class EpamJava8 {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        Map<String, List<String>> maps = new HashMap<String, List<String>>();
        maps.put("Animals", Arrays.asList("Tiger","Dog","Cat","Deer","Monkey","SPARROW","Tiger","Cat")); // SPARROW
        maps.put("Fruits", Arrays.asList("apple","mango","banana","chiku")); // all ignore
        maps.put("Cities", Arrays.asList("Hyderabad","Pune","Mumbai","New York","Goa","Chennai","Pune")); // New York

        // combine

        List<String> animalList = maps.get("Animals").stream().filter(animal->!animal.equals("SPARROW")).collect(Collectors.toList()
                );
        List<String> cityList = maps.get("Cities").stream().filter(city->!city.equals("NewYork") && city.toString().length()>3).distinct().collect(Collectors.toList());

        animalList.addAll(cityList);

//        List<String> animalList1 = maps.get("Animals").stream().filter(animal->!animal.equals("SPARROW")).collect(Collectors.toList()
//        ).addAll(maps.get("Cities").stream().filter(city->!city.equals("NewYork") && city.toString().length()>3).distinct().collect(Collectors.toList()));

//        List<String> cityList1 = maps.get("Cities").stream().filter(city->!city.equals("NewYork") && city.toString().length()>3).distinct().collect(Collectors.toList()));
//
//        animalList.addAll(cityList);
    }
}