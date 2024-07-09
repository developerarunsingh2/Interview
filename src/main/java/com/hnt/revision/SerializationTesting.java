package com.hnt.revision;

import java.io.*;

public class SerializationTesting {

    public static void main(String[] args) throws FileNotFoundException {
        // Create an instance of the Serializable class
        Person person = new Person("Arun", 25);
        //Serialize the object
        try {
            FileOutputStream outputStream = new FileOutputStream("output");
            ObjectOutputStream oos = new ObjectOutputStream(outputStream);
            oos.writeObject(person);
            System.out.println("Object Serialized");
        } catch (Exception e) {

        }

        System.out.println("Deserialization");
        try {
            FileInputStream fis = new FileInputStream("output");

            ObjectInputStream ois = new ObjectInputStream(fis);
            Person p = (Person) ois.readObject();
        } catch (Exception e) {

        }

        System.out.println(person.getName() + " " + person.getAge());
    }
}