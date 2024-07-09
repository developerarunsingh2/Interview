package com.hnt.practice;

import java.util.HashSet;
import java.util.Set;

public class LongestSubstring {

    public static int lengthOfLongestSubstring(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int[] maxSubstr = new int[n]; // Stores length of longest substring ending at index i
        int maxLength = 0;

        for (int i = 0, j = 0; j < n; j++) {
            // If current character is already in the set, move the left pointer until the character is removed
            while (set.contains(s.charAt(j))) {
                set.remove(s.charAt(i++));
            }
            // Add the current character to the set
            set.add(s.charAt(j));
            // Update the maximum length of the substring
            maxSubstr[j] = j - i + 1;
            // Update the overall maximum length
            maxLength = Math.max(maxLength, maxSubstr[j]);
        }
        return maxLength;
    }

    public static void main(String[] args) {
        String input = "abcabcbb";
        int longestLength = lengthOfLongestSubstring(input);
        System.out.println("Longest substring without repeating characters: " + longestLength);
    }
}
