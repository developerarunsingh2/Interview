package com.hnt.practice;

import java.io.*;

public class SerializationExample {
    public static void main(String[] args) throws FileNotFoundException {
        Student student = new Student("Ram",1);

//        serializeObject(student);
    }

    private static void serializeObject(Student student) throws IOException {

        FileOutputStream fos = new FileOutputStream("student.rec");
        ObjectOutputStream oos = new ObjectOutputStream(fos);

    }
}
