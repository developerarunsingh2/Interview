package com.practice.java8;

import com.practice.Department;
import com.practice.Employee;
import org.w3c.dom.ls.LSOutput;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SteamsAndCollections {
    public static List<Employee> removeDuplicate(List<Employee> e) {
        HashSet<Employee> set = new HashSet<>();
//        e = e.stream().filter(emp->set.add(emp)).collect(Collectors.toList());
        e = e.stream().distinct().collect(Collectors.toList());
        return e;
    }

    public static void main(String[] args) {
        /*Q1: Implement a method to remove duplicates from a list of objects using Java 8 Streams.
         * */
        Department itDept = new Department(1, "IT");
        Department hrDept = new Department(2, "HR");
        Department salesDept = new Department(3, "Sales");

        List<Employee> employeeList = new ArrayList<>();

        Employee emp1 = new Employee(1, "Alice", itDept, 75000);
        Employee emp2 = new Employee(2, "Bob", hrDept, 55000);
        Employee emp3 = new Employee(3, "Charlie", salesDept, 60000);

        // Duplicate employees
        Employee empDuplicate1 = new Employee(1, "Alice", itDept, 75000);
        Employee empDuplicate2 = new Employee(2, "Bob", hrDept, 55000);
        employeeList.add(emp1);
        employeeList.add(emp2);
        employeeList.add(emp3);
        employeeList.add(empDuplicate1);  // duplicate
        employeeList.add(empDuplicate2);  // duplicate

        removeDuplicate(employeeList).stream().forEach(System.out::println);

        /*Q2: Finding the nth highest salary from list of employee
         * */
        int n = 2;

        employeeList.stream().sorted(Comparator.comparing(Employee::getSalary).reversed()).
                map(p -> p.getSalary()).distinct().
                limit(n).collect(Collectors.toList()).forEach(System.out::println);
       /*
     Q3:  Group a list of employees by department and gender
       * */

        Map<Department, Map<String, List<Employee>>> map = employeeList.stream().collect(
                Collectors.groupingBy(Employee::getDepartmentName,
                        Collectors.groupingBy(Employee::getName)));

        System.out.println(map);

        /*
        Q4: Partition a list of integers into even and odd numbers
        * */

        List<Integer> integerList = List.of(1, 2, 3, 4, 5, 6, 7);

        List<Integer> evenList = integerList.stream().filter(number -> number % 2 == 0).collect(Collectors.toList());
     /*   List<Integer> oddList = integerList.stream().filter(number->number%2!=0).collect(Collectors.toList());

        System.out.println("even list: "+evenList);
        System.out.println("odd list: "+oddList);*/

        Map<Boolean, List<Integer>> partitionedMap = integerList.stream()
                .collect(Collectors
                        .partitioningBy(
                                number -> number % 2 == 0
                        )
                );

        System.out.println("Even numbers: " + partitionedMap.get(true));
        System.out.println("Odd numbers: " + partitionedMap.get(false));

        /*
         * Q5: Flatten a list of lists using flatMap
         * */

        List<List<Employee>> employeeListOfList = new ArrayList<>();
        employeeListOfList.add(employeeList);
        System.out.println("original list without flatten: " + employeeListOfList);
        List<Employee> employeeListOfListFlattend = employeeListOfList.stream()
                .flatMap(List::stream).collect(Collectors.toList());
        System.out.println("original list without flatten: " + employeeListOfList);
        System.out.println("flattened list: " + employeeListOfListFlattend);

        /* Q6: Counting the frequency of each word in a text using streams
         * */
        String str = "Arun Kumar Singh My name is Arun Kumar Singh";
        Map<String, Long> countingMap = Arrays.stream(str.split(" ")).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(countingMap);

        /*Q7: Sort a list of objects by multiple fields*/
        System.out.println("before sorting: " + employeeList);

        employeeList.stream()
                .sorted(Comparator.comparing(Employee::getSalary)
                        .thenComparing(Employee::getName))
                .collect(Collectors.toList());


        /*
         * Q8: Find the second largest element in the list */

        Integer secondLargest = integerList.stream()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst().get();
        System.out.println("second largest : " + secondLargest);

        /*
         * Filter a list of strings, remove empty, trim, and uppercase
         * */
        List<String> fruits = List.of("Apple", "Banana", "Carrot ", " ", "PAPAYA", " gRAPES");
        List<String> curatedFruitList = fruits.stream()
                // .filter(fruit->!fruit.equals(""))
                //  .map(fruit->fruit.trim())
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(fruit -> fruit.toUpperCase())
                .collect(Collectors.toList());
        System.out.println(curatedFruitList);


        /*
         * Merge two lists into a map based on a common key
         * */

        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        List<Integer> ages = Arrays.asList(25, 30, 35);

        Map<String, Integer> mergedMap = IntStream.range(0, names.size())
                .boxed()
                .collect(Collectors.toMap(names::get, ages::get));

        System.out.println("merged map: "+mergedMap);
    }
}
