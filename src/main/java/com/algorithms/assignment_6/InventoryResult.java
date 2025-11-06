package com.algorithms.assignment_6;

import java.util.List;

public class InventoryResult {
    private int maxPriority;
    private List<String> acceptedBatches;

    public InventoryResult(int maxPriority, List<String> acceptedBatches) {
        this.maxPriority = maxPriority;
        this.acceptedBatches = acceptedBatches;
    }

    public int getMaxPriority() {
        return maxPriority;
    }

    public List<String> getAcceptedBatches() {
        return acceptedBatches;
    }

    @Override
    public String toString() {
        return String.format("InventoryResult{maxPriority=%d, acceptedBatches=%s}", 
                             maxPriority, acceptedBatches);
    }
}
