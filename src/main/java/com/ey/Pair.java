package com.ey;

import java.util.HashMap;
import java.util.Map;

public class Pair {

    public static void main(String args[])
    {
//        How to find all pairs of integers whose sum is equal to a given number.
//        For example if input integer array is {2, 6, 3, 9, 11} and given sum is 9, output should be {6,3}.

        //2,7
        //6.3
        //3,6
        //9,0

        //11,-2

        int target = 9;
        Map<Integer,Integer> map = new HashMap<>();

        int arr[] = {2, 6, 3, 9, 11};

for(int i: arr)
{
    map.put(i,target-i);
}

for(int i: arr)
{
    for(int j: map.keySet())
    if(i+j==target)
    System.out.println("pair{"+i +","+j+"}");
}
    }
}
