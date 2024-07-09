package com.ey;

import java.util.Comparator;

public class Employee implements  Comparable<Employee>, Comparator<Employee>, Runnable {
    int salary;
    String name;
    String department;

public Employee(){}
    public Employee(int salary, String name, String department) {
        this.salary = salary;
        this.name = name;
        this.department = department;
    }

    public void setSalary(int salary)
    {
        this.salary = salary;
    }

    public int getSalary()
    {
        return salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "salary=" + salary +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                '}';
    }

    public int compareTo(Employee o) {
   return Integer.compare(this.getSalary(),o.getSalary());
    }

    @Override
    public int compare(Employee o1, Employee o2) {
        return 0;
    }

    public void run()
    {
        for(int i=0;i<5;i++)
        {
            System.out.println(Thread.currentThread().getName()+": value is : "+i);
        }
    }
}
