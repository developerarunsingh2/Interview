package com.hnt.revision;

public class Main {
    public static void main(String[] args) {
        // Using the static method to get a multiplication operation
        Multiplication multiplication = Multiplication.multiply();

        // Using the multiplication operation
        int result = multiplication.operate(3, 4);
        System.out.println("Result: " + result); // Output: 12

//Ser

    }
}