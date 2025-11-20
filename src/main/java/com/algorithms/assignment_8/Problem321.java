package com.algorithms.assignment_8;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

class Solution321 {
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int m = nums1.length;
        int n = nums2.length;
        int[] result = new int[k];

        for (int i = Math.max(0, k - n); i <= Math.min(k, m); i++) {
            int[] subsequence1 = findMaxSubsequence(nums1, i);
            int[] subsequence2 = findMaxSubsequence(nums2, k - i);
            int[] merged = merge(subsequence1, subsequence2);
            
            if (compare(merged, 0, result, 0) > 0) {
                result = merged;
            }
        }
        
        return result;
    }

    private int[] findMaxSubsequence(int[] nums, int k) {
        if (k == 0) {
            return new int[0];
        }
        if (k >= nums.length) {
            return nums;
        }
        
        Deque<Integer> stack = new ArrayDeque<>();
        int toRemove = nums.length - k;
        
        for (int num : nums) {
            while (!stack.isEmpty() && toRemove > 0 && stack.peek() < num) {
                stack.pop();
                toRemove--;
            }
            stack.push(num);
        }
        
        while (stack.size() > k) {
            stack.pop();
        }
        
        int[] result = new int[k];
        int idx = k - 1;
        while (!stack.isEmpty()) {
            result[idx--] = stack.pop();
        }
        
        return result;
    }

    private int[] merge(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int[] result = new int[m + n];
        int i = 0, j = 0, idx = 0;
        
        while (i < m && j < n) {
            if (compare(nums1, i, nums2, j) > 0) {
                result[idx++] = nums1[i++];
            } else {
                result[idx++] = nums2[j++];
            }
        }
        
        while (i < m) {
            result[idx++] = nums1[i++];
        }
        
        while (j < n) {
            result[idx++] = nums2[j++];
        }
        
        return result;
    }

    private int compare(int[] nums1, int i, int[] nums2, int j) {
        int m = nums1.length;
        int n = nums2.length;
        
        while (i < m && j < n) {
            int diff = nums1[i] - nums2[j];
            if (diff != 0) {
                return diff;
            }
            i++;
            j++;
        }
        
        return (m - i) - (n - j);
    }
}

