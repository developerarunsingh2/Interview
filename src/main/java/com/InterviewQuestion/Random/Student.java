package com.InterviewQuestion.Random;

public class Student {
    private int id;
    private String address;
    private double percentage;
    private String city;

    public Student(int id, String address, double percentage, String city) {
        this.id = id;
        this.address = address;
        this.percentage = percentage;
        this.city = city;
    }

    // Defaault Constructor
    public Student(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", percentage='" + percentage + '\'' +
                ", city='" + city + '\'' +
                '}';
    }


}
