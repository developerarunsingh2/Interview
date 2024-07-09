package com.hnt.jvmcourse;



public class EqualsExample {
    public static void main(String[] args) {
        Person p1 = new Person();
        p1.setName("Ram");
        Person p2 = new Person();
        p2.setName("Ram");

        System.out.println(p1.equals(p2));
    }
}
