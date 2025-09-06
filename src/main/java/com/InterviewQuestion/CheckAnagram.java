package com.InterviewQuestion;

import java.util.Arrays;

public class CheckAnagram {
    public static boolean isAnagram(String s, String t)
    {
        if(s.length()!=t.length())
        {
            return false;
        }

        char[] sArray = s.toCharArray();
        Arrays.sort(sArray);
        char[] tAray = t.toCharArray();
        Arrays.sort(tAray);

      for(int i=0;i<sArray.length;i++)
      {
          if(sArray[i]!=tAray[i])
          {
              return false;
          }
      }

       return true;
    }
    public static void main(String[] args) {
        // check anagrams having same characters

        System.out.println(isAnagram("racecar","carrace"));
    }
}
