package com.hnt;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

public class LongestSubstring {
        public static void main(String[] args) {
//            System.out.println("Hello, World!");

            //longest substring without any repeating chars
            //kyckyckk - kyc
            //pnnben - nbe
            //kyckyckkabcd - kabcd

            //condtions to check
            // no duplicate->set
            // contiguous -> i,j
            // longest   -> substring,longestsubstring
            System.out.println("Longest substring is " + find("abcdabcdefgh"));

            String str = "";

            List<char[]> characterList = Arrays.asList(str.toCharArray());

//            String longestSubstring = characterList.stream().filter()
        }

        private static String find(String word) {

            String longestSubstring = "";
            String subString = "";
            char[] charArray = word.toCharArray();

            LinkedHashSet<Character> set = new LinkedHashSet<>();

            for (int i = 0; i < charArray.length; i++) {
                for (int j = i+1; j<charArray.length-1; j++) {
//                    if (set.contains(charArray[j])) {
                    if (charArray[i]==charArray[j]) {
                        subString="";
                        break;
                    } else {
//                        set.add(charArray[j]);

                        subString = subString + charArray[j+1];

                        if (longestSubstring.length() < subString.length()) {
                            longestSubstring = subString;
                        }
                    }
                }



                     }
            return longestSubstring; }}



