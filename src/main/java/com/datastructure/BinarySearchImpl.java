package com.datastructure;

import java.util.Arrays;

import static java.util.Arrays.binarySearch;

public class BinarySearchImpl {
    static int binarySearching(int arr[], int target){
        int start = 0;
        int end = arr.length-1;

   while(start<end)
   {

       int mid = start+end/2;

       if(arr[mid]== target)
       {
           return mid;
       }
       if(target>arr[mid])
       {
           start = mid;
       }

       else if(target<arr[mid])
       {
           end = mid-1;
       }
   }
        return -1;
    }
    public static void main(String[] args) {
        int arr[] = {3, 2, 1, 10, 9, 12};
        int target = 10;
        //output 2
        Arrays.sort(arr);
        //O(nlogn)
        Arrays.stream(arr).forEach(System.out::println);
        //  System.out.println(arr.s);
        System.out.println(binarySearching(arr, target));
    }}