//package com.ey;
//
//import java.util.Arrays;
//
//public class TimeCalculator {
//
//
//
// /*   The problem is to find the time taken by function by the name should be calculated in a log file that has the format
//
//
//    Input:
//    timestamp  functionName Lable
//1652334332 Func1 START
//
//1652334333 Func1 START
//1652334335 Func1 END
//
//1652334337 Func1 END
//1652334338 Func2 START
//1652334340 Func2 END
//    Out:
//    Func1: 2s
//    Func1: 5s
//    Func2: 2s
//
//  */
//
////    public
//
//
//
//
//    /*
//    *
//    * Problem:
//Together with your friends you decided to go to a park. The public park is very narrow, thus only one group of people can occupy space between grass
//*  and the road:
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//TAKEN FREE FREE FREE TAKEN FREE FREE TAKEN
//*
//FREE FREE FREE TAKEN FREE FREE TAKEN
//*
//TAKEN TAKEN FREE FREE FREE
//—------------------------------
//===============================
//Part 1- You want more room for your party. Hence, you want to find the longest free space. How would you do this?
//Time and Space complexity?
//Part 2- Return The Start Position
//* */
//
//
//    int arr[] = {15,10,16,20,8,9,50};
//
//    public int secondLargest(int arr[])
//    {
////        Arrays.sort(arr);
//
//
//        // quick sort
//        //pivot start
//        //partition(pivot)
//        //quicksort(
//
//        int max = Integer.MIN_VALUE;
//        int secondMax = Integer.MIN_VALUE;
//
//        for(int i=0;i<arr.length;i++)
//        {
//         //   secondMax =
//            max = Math.max(arr[i],max);
//        }
//
//        for(int i=0;i<arr.length;i++)
//        {
//            //   secondMax =
//            max = Math.max(arr[i],max);
//        }
//
//
//
//
//
//       // return arr(arr.length-2);
//    }
//
//
//
//
//
//
//
//
//
//    public static String inputSpace(String str)
//    {
//        String[] strArray = str.split(" ");
//        int maxFreeSpaceCount = 1;
//        int maxCount = Integer.MAX_VALUE;
//        int index = 0;
//
//        for(int i=0;i<strArray.length-1;i++)
//        {
//
//            if(strArray[i]=="FREE" && strArray[i++])
//            {
//                index = i;
//                maxFreeSpaceCount++;
//            }
//
//            maxFreeSpaceCount = Math.max()
//        }
//
//
//    }
//
//}
