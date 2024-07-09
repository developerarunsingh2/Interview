package com.hnt.practice;

import java.util.Arrays;

public class Array_Functions {

    public static void main(String args[])
    {
        int arr[] = {3,6,9,12,15,18,22};

        System.out.println("");
        System.out.println(Arrays.binarySearch(arr,18));
        int copy[]=Arrays.copyOf(arr,3);
        int copyRange[] = Arrays.copyOfRange(arr,3,5);
        for(int a: copy)
            System.out.print(" "+a);

        for(int a:copyRange)
            System.out.println("r : "+a);

        int deepEquals[][] = {{1,2,3},{4,5,6}};
        int deepEquals1[][] = {{1,2,3},{4,5,6}};

        System.out.println("its deep equal comparision : " +Arrays.deepEquals(deepEquals1,deepEquals));
        System.out.println("arrays deep hascode method: " +Arrays.deepHashCode(deepEquals));
        System.out.println("deepToString() : "+Arrays.deepToString(deepEquals));
        int arrayFill[] = new int[40];
        Arrays.fill(arrayFill,5);

        for(int a: arrayFill)
            System.out.println("arrays fill method element : "+a);

       System.out.println(Arrays.hashCode(arrayFill));

        int arr_parallel_prefix[] = {3,6,9,12,15,18,22};
     Arrays.parallelPrefix(arr_parallel_prefix,(x,y)->x+y);

//     for(int a: arr_parallel_prefix)
//     {
//         System.out.println("modified array " + a);
//     }

        System.out.println("modified array: ");
     Arrays.stream(arr_parallel_prefix).forEach(n->System.out.print(" "+n));
//        int arr_parallel_setAll[] = {3,6,9,12,15,18,22};

        int[] arr_parallel_setAll = new int[10];

        // Set all elements of the array using a generator function
        Arrays.parallelSetAll(arr_parallel_setAll, index -> index * 2);

        // Print the modified array
        System.out.println("Array after parallel set all: " + Arrays.toString(arr_parallel_setAll));

        int [] arr_parallel_sort = {3,1,8,4,9};
        Arrays.parallelSort(arr_parallel_sort);

System.out.println("parrel sort: ");
        for(int a: arr_parallel_sort)
        {
            System.out.print(" : "+ a);
        }


Arrays.sort(arr);


        int [] arr_spilator = {3,1,8,4,9};

        Arrays.spliterator(arr_spilator);
        System.out.println();
        System.out.println("spliterator array:");
       Arrays.stream(arr_spilator).forEach(n->System.out.print(" "+n));
        int [] mismatch = {3,1,8,4,9};
        int [] mis_match = {3,1,8,4,9,12};

      //  Arrays.compare(mismatch,mis_match); not working giving error.
        Arrays.stream(arr_spilator).summaryStatistics();


//int a = Arrays.mismatch(mismatch,mis_match); works with java 9




    }
}
