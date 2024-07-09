package com.ey;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class DeadLockThread implements Runnable {

    public static void main(String args[]) throws InterruptedException {
        //
        List<Employee> list = new ArrayList<>();
        Employee e1 = new Employee();
        Employee e2 = new Employee();
        Employee e3 = new Employee();
        Employee e4 = new Employee();
        e1.setSalary(10);
        e2.setSalary(5);
        e3.setSalary(2);
        e4.setSalary(12);
        list.add(e1);
        list.add(e2);
        list.add(e3);
        list.add(e4);


        List<Employee>sortedList1 = list.stream().sorted((e5,e6)->{
            return e5.getSalary()-e6.getSalary();
        }).collect(Collectors.toList());

        System.out.println("after sorted"+sortedList1);

        List<Employee>sortedList = list.stream().sorted(Comparator.comparingInt(Employee::getSalary))
                .collect(Collectors.toList());

        for(Employee e: sortedList)
        System.out.println(e.getSalary());

        // printing without for loop
        int salary = e1.getSalary();
        list.stream().sorted(Comparator.comparingInt(Employee::getSalary)).forEach((System.out::println));


//        Runnable r = new Thread();
//        Runnable r1 = new Thread();
//        Thread t = new Thread(r);
//        Thread t2 = new Thread(r1);
//        t.start();
//
//
////        t.join();
//        t2.start();
//
//    }
//
//    @Override
//    public void  run() {
//
//        for(;;){
//            try {
//                System.out.println("Executing thread");
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }




    }

    @Override
    public void run() {

    }
}
