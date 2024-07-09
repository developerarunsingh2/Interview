package com.ey;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Spliterator;

public class ComparableVsComparator{
    public static void main(String args[])
    {
        Employee e1 = new Employee(1,"Ram","IT");
        Employee e2= new Employee(2,"Shiva","LG");


        System.out.println(e1.compareTo(e2));

        HashSet<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);

        Iterator<Integer> iterator = set.iterator();

        while(iterator.hasNext())
        {
            System.out.println(iterator.next());
        }

        Spliterator<Integer> spliterator = set.spliterator();
        spliterator.forEachRemaining(System.out::println);
    }

   /* SOLID: single responsibility principle: for class and interfaces
    O: open close principle: open for extension, close for modification
    L : liscove substitution principle: interitence: parent c replacable by child
    I: interface segregation principle: abstraction: unique work , not forced to implement in any class
    :D: dependency inversion principle: high-levlel module should not depend on low level modules*/



}
