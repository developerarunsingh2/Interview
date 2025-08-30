package com.datastructure;

import java.util.Arrays;

public class QuckSorting {

   static int[] swap(int first, int second)
    {
        int temp = first;
         first= second;
        second =temp;

        int second1 = second;
        return new int[]{first,second};
    }

    public static int[] quickSort(int arr[]){
    int pivot = arr[0];
    int left = 0;
    int right = 0 ;
    int leftArray[] = new int[arr.length];
    int rightSubArray[] = new int[arr.length];
    int i = 0;

    while(leftArray.length>1||rightSubArray.length>=1){

        quickSort(arr);

    }

       return arr;
    }
    public static void main(String[] args) {
        int arr[] = {3,2,1,10,9,12};
        arr = quickSort(arr);
        Arrays.stream(arr).forEach(System.out::println);
    }
}

