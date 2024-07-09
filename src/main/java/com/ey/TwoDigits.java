package com.ey;

import java.util.HashSet;
import java.util.Set;

public class TwoDigits {
    public static void main(String args[])
    {
        int number = 1;

//123-> 12->1
//        Program to find if a given number has more than 2 numbers in it should return ‘y’ or ‘n’ Example - 12 should return
//        ‘y as it has 2 digits 122 also has 2 unique digits so return y’ where as number 125 has 3 different digits in it so it should return n’
//        has context menu

        Set<Integer>hasSet = new HashSet<>();

        while(number>0){
            int inputSet = number%10;
            hasSet.add(inputSet);
           number =  number/10;
        }

        if(hasSet.size()>2)
        {
            System.out.println("y");
        }
        else{
            System.out.println("n");
        }
    }
}
