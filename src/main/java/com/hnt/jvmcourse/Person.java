package com.hnt.jvmcourse;

public class Person
{
    private String name;
    private String dob;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public boolean equals(Person obj) {
        return obj.getName().equals(this.getName());
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", dob='" + dob + '\'' +
                '}';
    }

    public Person(String name, String dob) {
        this.name = name;
        this.dob = dob;
    }

    public Person() {
    }
}
