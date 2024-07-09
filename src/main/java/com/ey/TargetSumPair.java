package com.ey;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TargetSumPair {
    public static List<Integer[]> findPairs(int[] arr, int target) {
        List<Integer[]> pairs = new ArrayList<>();
        Set<Integer> numSet = new HashSet<>();

        for (int num : arr) {
            int diff = target - num;
            if (numSet.contains(diff)) {
                pairs.add(new Integer[]{num, diff});
            }
            numSet.add(num);
        }

        return pairs;
    }

    public static void main(String[] args) {
        int[] arr = {2, 6, 3, 9, 11};
        int targetSum = 9;
        List<Integer[]> result = findPairs(arr, targetSum);
        System.out.println("Pairs with sum " + targetSum + " are:");
        for (Integer[] pair : result) {
            System.out.println(pair[0] + " " + pair[1]);
        }
    }
}
