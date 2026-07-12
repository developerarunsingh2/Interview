package com.datastructure;

import java.util.Arrays;

public class QuckSorting {

    // Swap two elements in an array
    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // Partition method: places pivot in correct position and returns its index
    static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    // Main quickSort method
    public static void quickSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        quickSort(arr, 0, arr.length - 1);
    }

    // Helper method for recursive quickSort
    private static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    public static void main(String[] args) {
        int[] arr = {3, 2, 1, 10, 9, 12};
        System.out.println("Original array: " + Arrays.toString(arr));
        quickSort(arr);
        System.out.println("Sorted array: " + Arrays.toString(arr));
        Arrays.stream(arr).forEach(System.out::println);
    }
}

