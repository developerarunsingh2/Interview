package com.hnt.jvmcourse;

public class ExampleString {
    public static void main(String[] args) {
        String name = "maaika";
        String dog = "Java";
        String cat = "Cucumber";
        String rabbit = new String("maaika");

        Person p = new Person();
        p.setName("maaika");
        p.setDob("1991");

        System.out.println(name==rabbit);
        System.out.println(name==p.getName());
    }
}
