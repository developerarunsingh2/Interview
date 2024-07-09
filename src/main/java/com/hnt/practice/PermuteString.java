package com.hnt.practice;

public class PermuteString {

    // Method to initiate permutation
    public static void permute(String str) {
        permuteHelper(str, "");
    }

    // Helper method to generate permutations recursively
    private static void permuteHelper(String str, String prefix) {
        int n = str.length();

        // Base case: if the string is empty, print the prefix
        if (n == 0) {
            System.out.println(prefix);
        } else {
            for (int i = 0; i < n; i++) {
                // Generate permutations for the remaining characters
                permuteHelper(str.substring(0, i) + str.substring(i + 1, n),
                        prefix + str.charAt(i));
            }
        }
    }

    public static void main(String[] args) {
        String str = "ABCD";
        System.out.println("Permutations of " + str + " are:");
        permute(str);
    }
}
