package com.ey;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

public class ListOfEmployeeWithDepartment {

    public static void main(String args[])
    {
        Employee e1 = new Employee(5,"Sivam","IT");
        Employee e2 = new Employee(9,"Rahul","Finance");
        Employee e3 = new Employee(6,"Siva","IT");
        Employee e4 = new Employee(2,"Suraj","Finance");

        List<Employee> list=new ArrayList<>();
        list.addAll(List.of(e1,e2,e3,e4));
//        list.add(e1);
//        list.add(e2);
//        list.add(e3);
//        list.add(e4);

        Map<String,List<Employee>> groupedMap = list.stream().collect(Collectors.groupingBy(Employee::getDepartment));

        System.out.println("list of employee as per department: "+groupedMap);

        Employee withMinSalaryEmployee = list.stream().min((e7,e8)->e7.getSalary()-e8.getSalary()).get();

        System.out.println("Minimum salary employee is "+withMinSalaryEmployee);

        OptionalDouble averageSalary = list.stream().mapToDouble((e9)->e9.getSalary()).average();

        System.out.println("average salary is" +averageSalary);

        // peek method

        Map<String,List<Employee>> groupedMap1 = list.stream().peek(e->System.out.println("department data processed"+e.department))
                .collect(Collectors.groupingBy(Employee::getDepartment));




    }
}

//for Registration:
//
//        User user = dao.save
//
//api:



//meeting
//
//s3,emr, airflow, secret manager,









