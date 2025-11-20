package com.algorithms.assignment_8;

import java.util.ArrayList;
import java.util.List;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        
        inOrderTraversal(root1, list1);
        inOrderTraversal(root2, list2);
        
        return mergeSortedLists(list1, list2);
    }
    
    private void inOrderTraversal(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        inOrderTraversal(root.left, result);
        result.add(root.val);
        inOrderTraversal(root.right, result);
    }
    
    private List<Integer> mergeSortedLists(List<Integer> list1, List<Integer> list2) {
        List<Integer> merged = new ArrayList<>();
        int i = 0, j = 0;
        
        while (i < list1.size() && j < list2.size()) {
            if (list1.get(i) <= list2.get(j)) {
                merged.add(list1.get(i++));
            } else {
                merged.add(list2.get(j++));
            }
        }
        
        while (i < list1.size()) {
            merged.add(list1.get(i++));
        }
        
        while (j < list2.size()) {
            merged.add(list2.get(j++));
        }
        
        return merged;
    }
}
