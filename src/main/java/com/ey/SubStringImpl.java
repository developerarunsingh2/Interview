package com.ey;

import java.util.ArrayList;
import java.util.List;

public class SubStringImpl {

    public static String shortestSubString(String S1, String S2)
    {

        char [] S1CharArray = S1.toCharArray();
        char [] S2CharArray = S2.toCharArray();
        int shortestLength = S1.length();
        int start = 0;
        int end = S1.length()-1;
        List<Character> list = new ArrayList<>();



        return S2;
    }

    public static void main(String args[])
    {
        String S1 = "ADOBECODEBANC";

        // ADOB
        String S2 = "ABC";
    }


}
