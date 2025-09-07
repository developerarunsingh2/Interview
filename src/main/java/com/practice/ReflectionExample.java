package com.practice;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionExample {
    public static void main(String[] args) throws Exception {
        // Get the Class object for Person
        Class<?> personClass = Class.forName("Person");

        // Instantiate the object using a private constructor
        Constructor<?> constructor = personClass.getDeclaredConstructor(String.class, int.class);
        constructor.setAccessible(true); // Allow access to private constructor
        Object personObject = constructor.newInstance("Alice", 30);

        // Access and modify a private field
        Field nameField = personClass.getDeclaredField("name");
        nameField.setAccessible(true); // Allow access to private field
        nameField.set(personObject, "Bob");
        System.out.println("Modified Name: " + nameField.get(personObject));

        // Invoke a private method
        Method getDetailsMethod = personClass.getDeclaredMethod("getDetails");
        getDetailsMethod.setAccessible(true); // Allow access to private method
        String details = (String) getDetailsMethod.invoke(personObject);
        System.out.println("Details from private method: " + details);
    }
}
