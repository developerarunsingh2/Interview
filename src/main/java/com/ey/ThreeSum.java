package com.ey;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ThreeSum {
    /*
    * Three sum problem:
Input:
 { 1, 4, 45, 6, 10, 8 };
 			  sum = 22;
Output:
	4, 10, 8
	*
	* */

    public static List<List<Integer>> findTriplets(int arr[], int sum)
    {
        List<List<Integer>> tripletList = new ArrayList<>();
        Arrays.sort(arr);
        int start = 0;
        int end = arr.length-1;
        int i=0;

        while(i<arr.length) {
        while(start<end && start!=i && end!=i) {

            List<Integer> innerList = new ArrayList<>();

            if ((arr[start] + arr[end]) == (sum - arr[i])) {

                innerList.add(arr[i]);
                innerList.add(arr[start]);
                innerList.add(arr[end]);
                tripletList.add(innerList);

            } else if ((arr[start] + arr[end]) > (sum - arr[i])) {
                end--;
            } else {
                start++;
            }
        }
        start=0;
        end= arr.length-1;
            i++;
        }
        return tripletList;
    }

    public static void main(String[] args) {
        int arr[] = { 1, 4, 45, 6, 10, 8 };
        //{1,4,6,8,10,45}
        int sum = 22;
        System.out.println(findTriplets(arr,sum));
    }

}
