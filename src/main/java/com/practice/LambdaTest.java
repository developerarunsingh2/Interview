package com.practice;

public interface LambdaTest {
    public int functionalMethodToAdd(int a, int b);
    static int staticMethod(){
        System.out.println("running interface static method for utility and cannot be overridden");
        return 10;
    }
}
