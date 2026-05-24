package com.practice.java8;

import java.util.HashMap;
import java.util.Map;

public class TCSInterview {
    public static void main(String[] args) {
        String s = "Panama";
        //A1
        Map<Character, Integer> charCount = new HashMap<>();
       // char[] charArrays = s.toCharArray();

        for(int i=0;i<s.length();i++)
        {
            if(charCount.containsKey(s.charAt(i))){
                charCount.put(s.charAt(i),charCount.get(i)+1);
            }

            else {
                charCount.put(s.charAt(i),1);
            }
        }


    }
}
