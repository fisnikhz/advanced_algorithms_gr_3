package com.algorithms.assignment_8;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=".repeat(60));
        System.out.println("Problem 1305: All Elements in Two Binary Search Trees");
        System.out.println("=".repeat(60));
        System.out.println();

        Solution solution = new Solution();

        System.out.println("Example 1:");
        TreeNode root1_ex1 = new TreeNode(2,
                new TreeNode(1),
                new TreeNode(4));
        TreeNode root2_ex1 = new TreeNode(1,
                new TreeNode(0),
                new TreeNode(3));
        List<Integer> result1 = solution.getAllElements(root1_ex1, root2_ex1);
        List<Integer> expected1 = List.of(0, 1, 1, 2, 3, 4);
        boolean passed1 = expected1.equals(result1);
        System.out.println("Input: root1 = [2,1,4], root2 = [1,0,3]");
        System.out.println("Expected: " + expected1);
        System.out.println("Result:   " + result1);
        System.out.println(passed1 ? "[PASS]" : "[FAIL]");
        System.out.println();

        System.out.println("Example 2:");
        TreeNode root1_ex2 = new TreeNode(1,
                null,
                new TreeNode(8));
        TreeNode root2_ex2 = new TreeNode(8,
                new TreeNode(1),
                null);
        List<Integer> result2 = solution.getAllElements(root1_ex2, root2_ex2);
        List<Integer> expected2 = List.of(1, 1, 8, 8);
        boolean passed2 = expected2.equals(result2);
        System.out.println("Input: root1 = [1,null,8], root2 = [8,1]");
        System.out.println("Expected: " + expected2);
        System.out.println("Result:   " + result2);
        System.out.println(passed2 ? "[PASS]" : "[FAIL]");
        System.out.println();

        System.out.println("Edge Case 1: Both trees are empty");
        List<Integer> result3 = solution.getAllElements(null, null);
        List<Integer> expected3 = List.of();
        boolean passed3 = expected3.equals(result3);
        System.out.println("Input: root1 = null, root2 = null");
        System.out.println("Expected: " + expected3);
        System.out.println("Result:   " + result3);
        System.out.println(passed3 ? "[PASS]" : "[FAIL]");
        System.out.println();

        System.out.println("Edge Case 2: One tree is empty");
        TreeNode root1_ex4 = new TreeNode(5,
                new TreeNode(3),
                new TreeNode(7));
        List<Integer> result4 = solution.getAllElements(root1_ex4, null);
        List<Integer> expected4 = List.of(3, 5, 7);
        boolean passed4 = expected4.equals(result4);
        System.out.println("Input: root1 = [5,3,7], root2 = null");
        System.out.println("Expected: " + expected4);
        System.out.println("Result:   " + result4);
        System.out.println(passed4 ? "[PASS]" : "[FAIL]");
        System.out.println();

        System.out.println("Edge Case 3: Single node trees");
        TreeNode root1_ex5 = new TreeNode(10);
        TreeNode root2_ex5 = new TreeNode(5);
        List<Integer> result5 = solution.getAllElements(root1_ex5, root2_ex5);
        List<Integer> expected5 = List.of(5, 10);
        boolean passed5 = expected5.equals(result5);
        System.out.println("Input: root1 = [10], root2 = [5]");
        System.out.println("Expected: " + expected5);
        System.out.println("Result:   " + result5);
        System.out.println(passed5 ? "[PASS]" : "[FAIL]");
        System.out.println();

        System.out.println("=".repeat(60));
        int totalTests = 5;
        int passedTests = (passed1 ? 1 : 0) + (passed2 ? 1 : 0) + (passed3 ? 1 : 0) +
                         (passed4 ? 1 : 0) + (passed5 ? 1 : 0);
        System.out.println("Summary: " + passedTests + "/" + totalTests + " tests passed");
        System.out.println("=".repeat(60));
    }
}

