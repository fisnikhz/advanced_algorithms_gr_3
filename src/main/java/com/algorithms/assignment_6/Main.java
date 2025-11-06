package com.algorithms.assignment_6;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("=".repeat(60));
        System.out.println("Warehouse Inventory Optimization - Dynamic Programming");
        System.out.println("=".repeat(60));
        System.out.println();

        testExample1();
        
        testExample2();
        
        additionalTests();
    }

    private static void testExample1() {
        System.out.println("EXAMPLE 1:");
        System.out.println("-".repeat(60));
        
        int capacity = 50;
        List<Batch> batches = Arrays.asList(
            new Batch("B001", 10, 60),
            new Batch("B002", 20, 100),
            new Batch("B003", 30, 120),
            new Batch("B004", 15, 80)
        );

        System.out.println("Capacity: " + capacity);
        System.out.println("Batches:");
        for (Batch batch : batches) {
            System.out.println("  " + batch);
        }

        InventoryResult result = WarehouseOptimizer.optimizeInventory(capacity, batches);
        
        System.out.println("\nResult: " + result);
        System.out.println("Expected: InventoryResult{maxPriority=240, acceptedBatches=[B001, B002, B004]}");
        System.out.println("Explanation: B001(10,60) + B002(20,100) + B004(15,80) = 45 units, 240 priority");
        
        // Verify
        boolean correct = result.getMaxPriority() == 240 &&
                         result.getAcceptedBatches().equals(Arrays.asList("B001", "B002", "B004"));
        System.out.println("Test Result: " + (correct ? "✓ PASSED" : "✗ FAILED"));
    }

    private static void testExample2() {
        System.out.println("EXAMPLE 2:");
        System.out.println("-".repeat(60));
        
        int capacity = 100;
        List<Batch> batches = Arrays.asList(
            new Batch("X1", 25, 150),
            new Batch("X2", 40, 200),
            new Batch("X3", 30, 180),
            new Batch("X4", 35, 220),
            new Batch("X5", 20, 100)
        );

        System.out.println("Capacity: " + capacity);
        System.out.println("Batches:");
        for (Batch batch : batches) {
            System.out.println("  " + batch);
        }

        InventoryResult result = WarehouseOptimizer.optimizeInventory(capacity, batches);
        
        System.out.println("\nResult: " + result);
        System.out.println("Expected: InventoryResult{maxPriority=570, acceptedBatches=[X1, X2, X4]}");
        System.out.println("Explanation: X1(25,150) + X2(40,200) + X4(35,220) = 100 units, 570 priority");
        
        // Verify
        boolean correct = result.getMaxPriority() == 570 &&
                         result.getAcceptedBatches().equals(Arrays.asList("X1", "X2", "X4"));
        System.out.println("Test Result: " + (correct ? "✓ PASSED" : "✗ FAILED"));
    }

    private static void additionalTests() {
        System.out.println("ADDITIONAL TEST CASES:");
        System.out.println("=".repeat(60));
        
        System.out.println("\nTest 3 - Single batch that fits:");
        List<Batch> batches3 = Arrays.asList(new Batch("A1", 5, 100));
        InventoryResult result3 = WarehouseOptimizer.optimizeInventory(10, batches3);
        System.out.println("Result: " + result3);
        System.out.println("Expected: maxPriority=100, acceptedBatches=[A1]");
        
        System.out.println("\nTest 4 - No batches fit:");
        List<Batch> batches4 = Arrays.asList(
            new Batch("L1", 10, 100),
            new Batch("L2", 20, 200)
        );
        InventoryResult result4 = WarehouseOptimizer.optimizeInventory(5, batches4);
        System.out.println("Result: " + result4);
        System.out.println("Expected: maxPriority=0, acceptedBatches=[]");
        
        System.out.println("\nTest 5 - All batches fit:");
        List<Batch> batches5 = Arrays.asList(
            new Batch("S1", 10, 50),
            new Batch("S2", 15, 75),
            new Batch("S3", 20, 100)
        );
        InventoryResult result5 = WarehouseOptimizer.optimizeInventory(100, batches5);
        System.out.println("Result: " + result5);
        System.out.println("Expected: maxPriority=225, acceptedBatches=[S1, S2, S3]");
        
        System.out.println("\nTest 6 - Optimal choice test:");
        List<Batch> batches6 = Arrays.asList(
            new Batch("H1", 50, 250),
            new Batch("M1", 30, 180),
            new Batch("M2", 25, 160)
        );
        InventoryResult result6 = WarehouseOptimizer.optimizeInventory(60, batches6);
        System.out.println("Result: " + result6);
        System.out.println("Expected: maxPriority=340, acceptedBatches=[M1, M2]");
        System.out.println("Explanation: M1(30,180) + M2(25,160) = 340 > H1(50,250)");
    }
}
