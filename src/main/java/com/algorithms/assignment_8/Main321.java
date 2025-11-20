package com.algorithms.assignment_8;

import java.util.Arrays;

public class Main321 {
    public static void main(String[] args) {
        System.out.println("=".repeat(60));
        System.out.println("Problem 321: Create Maximum Number");
        System.out.println("=".repeat(60));
        System.out.println();

        Solution321 solution = new Solution321();

        System.out.println("Example 1:");
        int[] nums1_ex1 = {3, 4, 6, 5};
        int[] nums2_ex1 = {9, 1, 2, 5, 8, 3};
        int k1 = 5;
        int[] result1 = solution.maxNumber(nums1_ex1, nums2_ex1, k1);
        int[] expected1 = {9, 8, 6, 5, 3};
        boolean passed1 = Arrays.equals(result1, expected1);
        System.out.println("Input: nums1 = " + Arrays.toString(nums1_ex1) + 
                          ", nums2 = " + Arrays.toString(nums2_ex1) + ", k = " + k1);
        System.out.println("Expected: " + Arrays.toString(expected1));
        System.out.println("Result:   " + Arrays.toString(result1));
        System.out.println(passed1 ? "[PASS]" : "[FAIL]");
        System.out.println();

        System.out.println("Example 2:");
        int[] nums1_ex2 = {6, 7};
        int[] nums2_ex2 = {6, 0, 4};
        int k2 = 5;
        int[] result2 = solution.maxNumber(nums1_ex2, nums2_ex2, k2);
        int[] expected2 = {6, 7, 6, 0, 4};
        boolean passed2 = Arrays.equals(result2, expected2);
        System.out.println("Input: nums1 = " + Arrays.toString(nums1_ex2) + 
                          ", nums2 = " + Arrays.toString(nums2_ex2) + ", k = " + k2);
        System.out.println("Expected: " + Arrays.toString(expected2));
        System.out.println("Result:   " + Arrays.toString(result2));
        System.out.println(passed2 ? "[PASS]" : "[FAIL]");
        System.out.println();

        System.out.println("Example 3:");
        int[] nums1_ex3 = {3, 9};
        int[] nums2_ex3 = {8, 9};
        int k3 = 3;
        int[] result3 = solution.maxNumber(nums1_ex3, nums2_ex3, k3);
        int[] expected3 = {9, 8, 9};
        boolean passed3 = Arrays.equals(result3, expected3);
        System.out.println("Input: nums1 = " + Arrays.toString(nums1_ex3) + 
                          ", nums2 = " + Arrays.toString(nums2_ex3) + ", k = " + k3);
        System.out.println("Expected: " + Arrays.toString(expected3));
        System.out.println("Result:   " + Arrays.toString(result3));
        System.out.println(passed3 ? "[PASS]" : "[FAIL]");
        System.out.println();

        System.out.println("Edge Case 1: k equals sum of lengths");
        int[] nums1_ex4 = {1, 2};
        int[] nums2_ex4 = {3, 4};
        int k4 = 4;
        int[] result4 = solution.maxNumber(nums1_ex4, nums2_ex4, k4);
        int[] expected4 = {3, 4, 1, 2};
        boolean passed4 = Arrays.equals(result4, expected4);
        System.out.println("Input: nums1 = " + Arrays.toString(nums1_ex4) + 
                          ", nums2 = " + Arrays.toString(nums2_ex4) + ", k = " + k4);
        System.out.println("Expected: " + Arrays.toString(expected4));
        System.out.println("Result:   " + Arrays.toString(result4));
        System.out.println(passed4 ? "[PASS]" : "[FAIL]");
        System.out.println();

        System.out.println("Edge Case 2: k = 1");
        int[] nums1_ex5 = {9, 1, 2};
        int[] nums2_ex5 = {8, 7, 6};
        int k5 = 1;
        int[] result5 = solution.maxNumber(nums1_ex5, nums2_ex5, k5);
        int[] expected5 = {9};
        boolean passed5 = Arrays.equals(result5, expected5);
        System.out.println("Input: nums1 = " + Arrays.toString(nums1_ex5) + 
                          ", nums2 = " + Arrays.toString(nums2_ex5) + ", k = " + k5);
        System.out.println("Expected: " + Arrays.toString(expected5));
        System.out.println("Result:   " + Arrays.toString(result5));
        System.out.println(passed5 ? "[PASS]" : "[FAIL]");
        System.out.println();

        System.out.println("Edge Case 3: All digits from nums1");
        int[] nums1_ex6 = {9, 8, 7};
        int[] nums2_ex6 = {1, 2};
        int k6 = 3;
        int[] result6 = solution.maxNumber(nums1_ex6, nums2_ex6, k6);
        int[] expected6 = {9, 8, 7};
        boolean passed6 = Arrays.equals(result6, expected6);
        System.out.println("Input: nums1 = " + Arrays.toString(nums1_ex6) + 
                          ", nums2 = " + Arrays.toString(nums2_ex6) + ", k = " + k6);
        System.out.println("Expected: " + Arrays.toString(expected6));
        System.out.println("Result:   " + Arrays.toString(result6));
        System.out.println(passed6 ? "[PASS]" : "[FAIL]");
        System.out.println();

        System.out.println("Edge Case 4: Equal digits requiring proper merge");
        int[] nums1_ex7 = {6, 7};
        int[] nums2_ex7 = {6, 0, 4};
        int k7 = 5;
        int[] result7 = solution.maxNumber(nums1_ex7, nums2_ex7, k7);
        int[] expected7 = {6, 7, 6, 0, 4};
        boolean passed7 = Arrays.equals(result7, expected7);
        System.out.println("Input: nums1 = " + Arrays.toString(nums1_ex7) + 
                          ", nums2 = " + Arrays.toString(nums2_ex7) + ", k = " + k7);
        System.out.println("Expected: " + Arrays.toString(expected7));
        System.out.println("Result:   " + Arrays.toString(result7));
        System.out.println(passed7 ? "[PASS]" : "[FAIL]");
        System.out.println();

        System.out.println("=".repeat(60));
        int totalTests = 7;
        int passedTests = (passed1 ? 1 : 0) + (passed2 ? 1 : 0) + (passed3 ? 1 : 0) +
                         (passed4 ? 1 : 0) + (passed5 ? 1 : 0) + (passed6 ? 1 : 0) +
                         (passed7 ? 1 : 0);
        System.out.println("Summary: " + passedTests + "/" + totalTests + " tests passed");
        System.out.println("=".repeat(60));
    }
}

