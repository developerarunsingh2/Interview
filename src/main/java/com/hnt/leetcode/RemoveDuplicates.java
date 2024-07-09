package com.hnt.leetcode;

public class RemoveDuplicates {

    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;

        int count = 1;  // Number of occurrences of the current element
        int pos = 1;    // Position to place the next unique element

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                count++;
            } else {
                count = 1;
            }

            if (count <= 2) {
                nums[pos] = nums[i];
                pos++;
            }
        }

        return pos;
    }

    public static void main(String[] args) {
        RemoveDuplicates solution = new RemoveDuplicates();
        int[] nums = {1, 1, 1, 2, 2,2,2,2,2,2,3};
        int k = solution.removeDuplicates(nums);
        System.out.print("Array after removing duplicates: ");
        for (int i = 0; i < k; i++) {
            System.out.print(nums[i] + " ");
        }
        System.out.println();
        System.out.println("Length of modified array: " + k);
    }
}
