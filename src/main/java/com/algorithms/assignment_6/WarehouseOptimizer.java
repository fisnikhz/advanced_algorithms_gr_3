package com.algorithms.assignment_6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class WarehouseOptimizer {

    public static InventoryResult optimizeInventory(int shelfCapacity, List<Batch> batches) {
        int n = batches.size();
        
        int[][] dp = new int[n + 1][shelfCapacity + 1];
        
        for (int i = 1; i <= n; i++) {
            Batch batch = batches.get(i - 1);
            int size = batch.getSize();
            int priority = batch.getPriority();
            
            for (int w = 0; w <= shelfCapacity; w++) {
                dp[i][w] = dp[i - 1][w];
                
                if (size <= w) {
                    dp[i][w] = Math.max(dp[i][w], dp[i - 1][w - size] + priority);
                }
            }
        }
        
        List<String> acceptedBatches = new ArrayList<>();
        int remainingCapacity = shelfCapacity;
        
        for (int i = n; i > 0; i--) {
            if (dp[i][remainingCapacity] != dp[i - 1][remainingCapacity]) {
                Batch batch = batches.get(i - 1);
                acceptedBatches.add(batch.getId());
                remainingCapacity -= batch.getSize();
            }
        }
        
        Collections.reverse(acceptedBatches);
        
        return new InventoryResult(dp[n][shelfCapacity], acceptedBatches);
    }
}
