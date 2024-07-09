package com.practice;

import java.sql.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public static int[] twoSum(int[] nums, int target) {


        Map<Integer, Integer> map = new HashMap<>();
//        Map<Integer,Integer> indexMap = new HashMap<>();
//        int index = 0;

//      target
// calculating difference
        //

        for (int i = 0; i < nums.length; i++) {
//    indexMap.put(nums[i],i);
            int difference = target - nums[i];
            if (map.containsKey(difference)) {
                return new int[]{i, map.get(difference)};
            } else {
                map.put(nums[i], i);
            }
        }

// target = number + difference

//        for (int number : nums) {
//            int difference = target - number;
//
//            if (map.get(difference) != null) {
////     new Array.asList(indexMap.get(number),indexMap.get(difference));
//
//            } else {
//                map.put(number, difference);
//            }
//
//        }
//        return nums;
        return nums;
    }

    public  static void main(String args[]){
        int arr[] = {9,11,2,7,3,6};
        int target = 9;
int output[] =twoSum(arr,target);
      System.out.println(output[0]+","+output[1]);
    }
}