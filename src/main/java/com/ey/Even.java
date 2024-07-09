package com.ey;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Even {
    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(3);
//        list.add(10);
        list.add(4);

        list.remove(1);

        List<Integer>evenNumberList = list.stream().filter(num->num%2==0).collect(Collectors.toList());
        System.out.println(evenNumberList);
    }//

//    Employee , EmployeeDetails
//
//    select * from Employee  e
//            outer join EmployeeDetails ed
//    on e.id = ed.id;



}
