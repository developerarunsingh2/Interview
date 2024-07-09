package com.ey;

public class ReverseString {
    public static void main(String[] args) {
        String str = "String";
        StringBuilder builder = new StringBuilder(str);
        System.out.println("reversed String is: " +builder.reverse());
    }
}
