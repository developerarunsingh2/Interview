package com.ey;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FindDuplicateCharactersInArray {

    public static void findDuplicates(String str)
    {
        str.chars().mapToObj(c->(char) c)
                .filter(Character::isLetter)
                .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
                .entrySet()
                .stream()
                .filter(e->e.getValue()>1)
                .forEach(e->System.out.println(e.getKey()+": "+e.getValue()));
    }

    public static void main(String args[])
    {
        findDuplicates("Arun Kumar Singh");

/*//        get name of first ten employees sorted in ascending order based on salary,
//
//            having salary greater than 10,000/- asc*/

        List<Employee> employeeList = List.of(new Employee(),
                new Employee(12000,"Ram","IT"),
                new Employee(100,"Test1","sr"),
                new Employee(123456,"Test2","sales"),
                new Employee(123,"Test3","med"),
                new Employee(2345,"Test4","ex")
        );

List<String>names2 = employeeList.stream().filter(e->e.getSalary()>10000).map(m->m.getName())
        .sorted()
        .limit(2)
        .collect(Collectors.toList());
//employeeList.stream()

//            .filter(n->n.getSalary()>10000)
//            .sorted((o1,o2)->o1.getSalary()
//                    .compareTo(o2.getSalary())
//                    .limit(10)
//                    .map(n->n.getNames()).collect(Collector.toList());
        System.out.println(names2);

       List<String> droppedList = names2.stream().dropWhile(n->n.startsWith("R")).collect(Collectors.toList());
        System.out.println("droppedList"+names2);
    }
}
