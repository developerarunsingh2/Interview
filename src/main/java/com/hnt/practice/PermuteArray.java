package com.hnt.practice;

import java.util.ArrayList;
import java.util.List;

public class PermuteArray {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        generatePermutations(nums, used, current, result);
        return result;
    }

    private void generatePermutations(int[] nums, boolean[] used, List<Integer> current, List<List<Integer>> result) {
        if (current.size() == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                current.add(nums[i]);
                used[i] = true;
                generatePermutations(nums, used, current, result);
                current.remove(current.size() - 1);
                used[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        PermuteArray permutations = new PermuteArray();
        List<List<Integer>> result = permutations.permute(nums);
        System.out.println("All possible permutations:");
        for (List<Integer> perm : result) {
            System.out.println(perm);
        }
    }
}
