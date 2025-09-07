package com.practice;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class LongestSubstringLength {
    public static void main(String[] args) {
        String input = "abcabcbb";
        int maxSubStringLenth = 0;
        int start = 0;
        //     int end = 0 ;
        Set<Character> set = new LinkedHashSet<>();

        for (int end = 0; end < input.length(); end++) {
         /*   if(set.add(inputCharArray[i]))
            {
                end++;
            }
            else {
                set.remove(inputCharArray[i]);
                start++;
                set.add(inputCharArray[i]);
            }
            maxSubStringLenth = Math.max(maxSubStringLenth,end-start+1);
        }*/

            while (set.contains(input.charAt(end))) {
                set.remove(input.charAt(end));
                start++;
            }

            set.add(input.charAt(end));
            maxSubStringLenth = Math.max(maxSubStringLenth, end - start + 1);

        }
        System.out.println(maxSubStringLenth);
    }
}
