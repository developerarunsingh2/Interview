package com.hnt.practice;

public class IBMTest {
    public static void main(String[] args) {
        String str = "ABCD";

//        System.out.println(conversion(str));
        StringBuilder result = new StringBuilder();
        int shift = 2;
        for (char c : str.toCharArray()) {
            char shiftedChar = (char) (c - shift);
            if(shiftedChar<'A')
            {
                shiftedChar+=26;
            }
            result.append(shiftedChar);
        }

        System.out.println("converted string " +result.toString());
    }


    private static String conversion(String str) {
        return null;
    }
}
