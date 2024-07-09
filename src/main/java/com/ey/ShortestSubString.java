package com.ey;

import java.util.HashMap;

public class ShortestSubString {
    public static String shortestSubstring(String s, String t) {
        if (s == null || t == null || s.length() < t.length())
            return "";

        HashMap<Character, Integer> charCount = new HashMap<>();
        for (char c : t.toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }

        int left = 0, right = 0, minLength = Integer.MAX_VALUE, minLeft = 0, minRight = 0;
        int count = t.length();

        while (right < s.length()) {
            char c = s.charAt(right);
            if (charCount.containsKey(c)) {
                if (charCount.get(c) > 0)
                    count--;
                charCount.put(c, charCount.get(c) - 1);
            }

            while (count == 0) {
                if (right - left + 1 < minLength) {
                    minLength = right - left + 1;
                    minLeft = left;
                    minRight = right;
                }

                char leftChar = s.charAt(left);
                if (charCount.containsKey(leftChar)) {
                    charCount.put(leftChar, charCount.get(leftChar) + 1);
                    if (charCount.get(leftChar) > 0)
                        count++;
                }
                left++;
            }
            right++;
        }

        return (minLength == Integer.MAX_VALUE) ? "" : s.substring(minLeft, minRight + 1);
    }

    public static void main(String[] args) {
        String s1 = "ADOBECODEBANC";
        String s2 = "ABC";
        System.out.println("Shortest substring containing all characters of S2 in S1: " + shortestSubstring(s1, s2));
    }
}
