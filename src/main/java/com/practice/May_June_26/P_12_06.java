package com.practice.May_June_26;

import com.practice.Department;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class P_12_06 {
    static void main() {
        // optimize your code with AI help
        Department d = new Department(1,"HR");
        Employee e1 = new Employee(1,"arun",d ,12.0);
        Employee e2 = new Employee(2,"shiva",d ,15.0);
        Employee e3 = new Employee(3,"ram",d ,8.0);
        Employee e4 = new Employee(4,"radhe",d ,10.0);
        Employee e5 = new Employee(5,"sivam",d ,6.0);
        Employee e6 = new Employee(1,"Gaurav",d ,40.0);
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(e1);
        employeeList.add(e2);
        employeeList.add(e3);
        employeeList.add(e4);
        employeeList.add(e5);
        employeeList.add(e6);

        List<Double>top5salary = employeeList
                .stream()
      //          .sorted(Employee::getSalary)      // this is incorrect because sorted method expects comparator where as this returns double
                .sorted(Comparator.comparing(
                        Employee::getSalary)
                        .reversed())
                .limit(5).map(Employee::getSalary).toList();
        System.out.println("top 5 salaries are: "+top5salary);
    }
}
