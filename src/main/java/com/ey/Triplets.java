package com.ey;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Triplets {

    public static List<List<Integer>> findTriplets(int arr[])
    {
        List<List<Integer>> tripletList = new ArrayList<>();

        Arrays.sort(arr);//-4,-1,-1,0,1,2

        int start = 1;
        int end = arr.length-1;
        int index=0;

        while(start<end && index<arr.length-1)
        {
            int sum = arr[start]+arr[end];
            if(-arr[index]==sum)
            {
                tripletList.add(List.of(arr[index],arr[start],arr[end]));
                while(start<end && arr[start]==arr[start+1])start++;
                while(start<end && arr[end]==arr[end-1])end--;
                start++;
                end--;
            }

            else if(sum>(-arr[index]))
            {
               end--;

            }

            else {
                start++;
            }
         index++;
        }

        return tripletList;
    }
    public static void main(String[] args) {

        int arr[]={-1,0,1,2,-1,-4};
        System.out.println(findTriplets(arr));

    }
}
