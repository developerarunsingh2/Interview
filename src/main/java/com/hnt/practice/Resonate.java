package com.hnt.practice;

public class Resonate {
    public static void main(String[] args) {
        int a = 10;
        int b = 20;

        a= a+b;
        b=a-b;
        a= a-b;
//        System.out.println("value of a is: "+a);

        System.out.println(factorial(5));
    }

    private static int factorial(int i) {
        int result = 1;
        if(i==1||i==0)
        {
            return 1;
        }

        else{
          result = i*factorial(i-1);
        }

        return result;
    }
}
