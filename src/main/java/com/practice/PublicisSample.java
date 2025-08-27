package com.practice;

import com.ey.SingleTon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PublicisSample {
    //flat list

    public static void main(String[] args) {
        List<List<String>> mainList = new ArrayList<>();
        List<String> l1= List.of("abc","def","ghi");
        List<String> l2= List.of("jkl","mno");
        mainList.add(l1);
        mainList.add(l2);
        List<String>flattenedList = mainList.stream().flatMap(List::stream).collect(Collectors.toList());
        System.out.println(flattenedList);
    }/*
//Employee ->name, empId, dept, sal:
    lambad to findout highest salary from each deparment
            or
    find the employee name who has highest salary*/

    Department department1 = new Department(1,"IT");
    Department department2= new Department(1,"Sales");
    Employee e1 = new Employee(1,"Ram",department1,123);
    Employee e2 = new Employee(2,"Shiva",department2,456);
    Employee e3 = new Employee(2,"Shivash",department1,789);
    Employee e4 = new Employee(2,"Ramesh",department2,101112);

    List<Employee> employees = Arrays.asList(e1,e2,e3,e4);

    // String employeeWithHighestSal = employees.stream().sorted(Comparator.comparing(Employee::getSalary));

    // SingleTon design pattern, factory design pattern.

    //Singleton,
    //multiple beans on same class


}
