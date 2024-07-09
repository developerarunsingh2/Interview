package com.ey;

public class pallindrome {

    public static boolean isPalindrome(String input)
    {
        int start = 0;
        int end = input.length()-1;
        while(start<=end)
        {
            if(input.charAt(start)!=input.charAt(end))
            {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
    public static void main(String[] args) {
        String input = "123321";
       System.out.println("Is given string is palindrome: "+isPalindrome(input));
    }
}
