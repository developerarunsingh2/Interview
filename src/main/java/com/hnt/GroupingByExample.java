package com.hnt;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupingByExample {
    public static void main(String[] args) {
        // Sample list of Person objects
        List<Person> persons = Arrays.asList(
                new Person("John", 30),
                new Person("Jane", 25),
                new Person("Doe", 30),
                new Person("Alice", 25)
        );

        // Grouping persons by their age
        Map<Integer, List<Person>> groupedByAge = persons.stream()
                .collect(Collectors.groupingBy(Person::getAge));

        // Printing the result
        groupedByAge.forEach((age, people) -> {
            System.out.println("Age: " + age);
            System.out.println("People: " + people);
        });
    }
}

class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
