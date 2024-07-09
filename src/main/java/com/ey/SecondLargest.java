package com.ey;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class SecondLargest {
    public static void main(String[] args) {

     List<Integer> list = List.of(10,5,4,6);
//
//        Optional<Integer> element = list.stream().sorted(Comparator.reverseOrder()).skip(1).findFirst();
//
//        if(element.isPresent())
//        {
//            System.out.println(element.get());
//        }


        int max = Integer.MIN_VALUE;
        int secondLarget = Integer.MIN_VALUE;

        for(int i=0;i<list.size();i++)
        {
            max = Math.max(max,list.get(i));
        }

        for(int i: list)
        {
            if(i!=max)
            {
                secondLarget = Math.max(i,secondLarget);
            }
        }

        System.out.println("Second largest element is: "+secondLarget);
    }
}
