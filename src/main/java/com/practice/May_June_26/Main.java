package com.practice.May_June_26;

import java.util.ArrayList;
import java.util.List;

class A {
    static List<Integer> list = new ArrayList<>(List.of(1, 2, 3, 4, 5));
    static List<Integer> getList() { return list; }
}

class B {
    public void addToList(int value) {
        A.list.add(value);  // directly modifying A's static list
    }
}

public class Main {
    public static void main(String[] args) {
        B b1 = new B();
        b1.addToList(10);

        System.out.println(A.getList().size()); // OUTPUT: 6
        System.out.println(A.list);             // OUTPUT: [1, 2, 3, 4, 5, 10]
    }
}
