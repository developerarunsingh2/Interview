package com.ey;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SpecialCharacter {
    public static void main(String[] args) {

        String input = "ab%%&&   123 ^^-";

        char [] charArray = input.toCharArray();
                List<Character> inputCharList =  new ArrayList<>();
       for(char c: charArray)
       {
           inputCharList.add(c);
       }

       Map<Character,Long> countMap = inputCharList.stream().filter(n-> !Character.isDigit(n) && !Character.isLetter(n)
      ).collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));

       System.out.println(countMap);


    }
}
