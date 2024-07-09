package com.ey;

import java.util.HashMap;
import java.util.Map;

public class SubArrayAverage {
    public static int countSubarraysWithAverage(int[] arr, int n) {
        int count = 0;
        int sum = 0;
        int targetSum;
        Map<Integer, Integer> prefixSumMap = new HashMap<>();
        prefixSumMap.put(0, 1);  // To handle cases where subarray starts from index 0

        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            targetSum = sum - n * (i + 1);

            if (prefixSumMap.containsKey(targetSum)) {
                count += prefixSumMap.get(targetSum);
            }

            prefixSumMap.put(sum - n * (i + 1), prefixSumMap.getOrDefault(sum - n * (i + 1), 0) + 1);
        }

        return count;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 2, 3, 1, 5};
        int n = 3;
        System.out.println("Number of subarrays with average " + n + " is: " + countSubarraysWithAverage(arr, n));
    }
}
