package com.InterviewQuestion.Random;

public class PowerOfTwo {
    public static void main(String[] args) {
        int input =48;

        if(input<0)
        {
            System.out.println("It is a negative number");
        }

        if(input>=0)
        while(input%2==0){
            input = input/2;
        }

        if(input%2==1){
            System.out.println("It is not power of 2");
        }

        else{
            System.out.println("It is power of 2");
        }
    }
}
