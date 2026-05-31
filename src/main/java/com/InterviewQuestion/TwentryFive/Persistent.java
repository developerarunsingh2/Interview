package com.InterviewQuestion.TwentryFive;

public class Persistent {

    private static boolean isPowerOf2(int input)
    {
        if(input ==1)
        {
            return true;
        }
        while(input>1)

        {
            if(input%2==0) {
                input = input / 2;
            }

            else{
               return false;
            }
        }

        return  true;
    }
    public static void main(String args[]){

        int input = 22;

        System.out.println(isPowerOf2(input));
    }
}
