package com.algorithms.assignment_9;

import java.util.*;

public class KnapsackProblem {

    static class Item {
        String name;
        int weight;
        int value;

        Item(String name, int weight, int value) {
            this.name = name;
            this.weight = weight;
            this.value = value;
        }

        @Override
        public String toString() {
            return String.format("%-25s | Weight: %3d kg | Value: $%,6d", name, weight, value);
        }
    }

    static class KnapsackResult {
        int maxValue;
        List<Item> selectedItems;
        int totalWeight;

        KnapsackResult(int maxValue, List<Item> selectedItems, int totalWeight) {
            this.maxValue = maxValue;
            this.selectedItems = selectedItems;
            this.totalWeight = totalWeight;
        }
    }

    public static KnapsackResult solveKnapsackDP(Item[] items, int capacity) {
        int n = items.length;
        int[][] dp = new int[n + 1][capacity + 1];

        for (int i = 1; i <= n; i++) {
            for (int w = 0; w <= capacity; w++) {
                if (items[i - 1].weight <= w) {
                    dp[i][w] = Math.max(
                            dp[i - 1][w],
                            dp[i - 1][w - items[i - 1].weight] + items[i - 1].value);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        List<Item> selectedItems = new ArrayList<>();
        int w = capacity;
        int totalWeight = 0;

        for (int i = n; i > 0; i--) {
            if (dp[i][w] != dp[i - 1][w]) {
                selectedItems.add(items[i - 1]);
                totalWeight += items[i - 1].weight;
                w -= items[i - 1].weight;
            }
        }

        Collections.reverse(selectedItems);
        return new KnapsackResult(dp[n][capacity], selectedItems, totalWeight);
    }

    public static KnapsackResult bruteForceSolution(Item[] items, int capacity) {
        int n = items.length;
        int maxValue = 0;
        List<Item> bestSelection = new ArrayList<>();
        int bestWeight = 0;

        for (int mask = 0; mask < (1 << n); mask++) {
            List<Item> currentSelection = new ArrayList<>();
            int currentWeight = 0;
            int currentValue = 0;

            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    currentSelection.add(items[i]);
                    currentWeight += items[i].weight;
                    currentValue += items[i].value;
                }
            }

            if (currentWeight <= capacity && currentValue > maxValue) {
                maxValue = currentValue;
                bestSelection = new ArrayList<>(currentSelection);
                bestWeight = currentWeight;
            }
        }

        return new KnapsackResult(maxValue, bestSelection, bestWeight);
    }

    public static void printDPTable(Item[] items, int capacity) {
        int n = items.length;
        int[][] dp = new int[n + 1][capacity + 1];

        for (int i = 1; i <= n; i++) {
            for (int w = 0; w <= capacity; w++) {
                if (items[i - 1].weight <= w) {
                    dp[i][w] = Math.max(
                            dp[i - 1][w],
                            dp[i - 1][w - items[i - 1].weight] + items[i - 1].value);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        System.out.println("\nDynamic Programming Table (showing key capacity values):");
        System.out.println("=".repeat(90));

        System.out.print("Item                     ");
        int[] keyCapacities = { 0, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100 };
        for (int cap : keyCapacities) {
            if (cap <= capacity) {
                System.out.printf("%6d", cap);
            }
        }
        System.out.println();
        System.out.println("-".repeat(90));

        for (int i = 0; i <= n; i++) {
            if (i == 0) {
                System.out.printf("%-25s", "No items");
            } else {
                System.out.printf("%-25s", items[i - 1].name.substring(0, Math.min(25, items[i - 1].name.length())));
            }

            for (int cap : keyCapacities) {
                if (cap <= capacity) {
                    System.out.printf("%6d", dp[i][cap]);
                }
            }
            System.out.println();
        }
        System.out.println("=".repeat(90));
    }

    public static void demonstrateKnapsack(Item[] items, int capacity) {
        System.out.println("0/1 KNAPSACK PROBLEM - STEP-BY-STEP SOLUTION");
        System.out.println("=".repeat(90));
        System.out.println("\nREAL-WORLD SCENARIO: Disaster Relief Supply Optimization");
        System.out.println("A humanitarian organization must load a rescue helicopter with critical");
        System.out.println("supplies for a disaster zone. The helicopter has limited capacity.");
        System.out.println("Which supplies should be loaded to maximize the relief value?");

        System.out.println("\nPROBLEM INSTANCE:");
        System.out.println("  Helicopter Capacity: " + capacity + " kg");
        System.out.println("  Available Items: " + items.length);
        System.out.println();

        System.out.println("AVAILABLE SUPPLIES:");
        System.out.println("-".repeat(90));
        for (int i = 0; i < items.length; i++) {
            System.out.printf("%2d. %s\n", i + 1, items[i]);
        }
        System.out.println("-".repeat(90));

        printDPTable(items, capacity);

        long dpStartTime = System.nanoTime();
        KnapsackResult dpResult = solveKnapsackDP(items, capacity);
        long dpEndTime = System.nanoTime();
        double dpTimeMs = (dpEndTime - dpStartTime) / 1_000_000.0;

        System.out.println("\nDYNAMIC PROGRAMMING SOLUTION:");
        System.out.println("=".repeat(90));
        System.out.println("Selected Items:");
        for (Item item : dpResult.selectedItems) {
            System.out.println("  ✓ " + item);
        }
        System.out.println("-".repeat(90));
        System.out.printf("Total Weight: %d kg (out of %d kg capacity)\n", dpResult.totalWeight, capacity);
        System.out.printf("Total Value:  $%,d\n", dpResult.maxValue);
        System.out.printf("Execution Time: %.4f ms\n", dpTimeMs);

        System.out.println("\nVERIFICATION: Brute Force Solution (checking all 2^" + items.length + " = "
                + (1 << items.length) + " combinations)");
        System.out.println("=".repeat(90));

        long bfStartTime = System.nanoTime();
        KnapsackResult bfResult = bruteForceSolution(items, capacity);
        long bfEndTime = System.nanoTime();
        double bfTimeMs = (bfEndTime - bfStartTime) / 1_000_000.0;

        System.out.printf("Brute Force Maximum Value: $%,d\n", bfResult.maxValue);
        System.out.printf("Brute Force Execution Time: %.4f ms\n", bfTimeMs);

        if (dpResult.maxValue == bfResult.maxValue) {
            System.out.println("✓ VERIFIED: Dynamic Programming solution is OPTIMAL!");
        } else {
            System.out.println("✗ ERROR: Solutions don't match!");
        }

        System.out.println("\nTIME COMPLEXITY ANALYSIS:");
        System.out.println("=".repeat(90));
        System.out.printf("Dynamic Programming: O(n × W) = O(%d × %d) = O(%,d) operations\n",
                items.length, capacity, items.length * capacity);
        System.out.printf("Brute Force:        O(2^n) = O(2^%d) = O(%,d) combinations\n",
                items.length, (1 << items.length));
        System.out.printf("Speedup Factor:      %.2fx faster with DP\n", bfTimeMs / dpTimeMs);

        System.out.println("\nWHY 0/1 KNAPSACK IS NP-COMPLETE:");
        System.out.println("=".repeat(90));
        System.out.println("1. DECISION PROBLEM VERSION:");
        System.out.println("   \"Is there a subset of items with total value ≥ V and weight ≤ W?\"");
        System.out.println("   This is NP-complete (reducible from Subset Sum).");
        System.out.println();
        System.out.println("2. NO KNOWN POLYNOMIAL-TIME ALGORITHM:");
        System.out.println("   - DP solution is O(n × W), which is PSEUDO-POLYNOMIAL");
        System.out.println("   - W (capacity) is part of the problem VALUE, not SIZE");
        System.out.println("   - If W is encoded in binary, it takes log(W) bits");
        System.out.println("   - True complexity: O(n × 2^(log W)) - exponential in input size!");
        System.out.println();
        System.out.println("3. VERIFICATION IS EASY (NP property):");
        System.out.println("   - Given a solution, we can verify it in O(n) time");
        System.out.println("   - Just sum weights and values - polynomial verification");
        System.out.println();
        System.out.println("4. EXPONENTIAL GROWTH:");
        System.out.println("   - Brute force: 2^n possible combinations");
        System.out.println("   - For n=10: 1,024 combinations");
        System.out.println("   - For n=20: 1,048,576 combinations");
        System.out.println("   - For n=30: 1,073,741,824 combinations (infeasible!)");
        System.out.println();
        System.out.println("5. COMPARISON WITH LCS (P Problem):");
        System.out.println("   - LCS: True polynomial O(m×n) - always efficient");
        System.out.println("   - Knapsack: Pseudo-polynomial - efficient only for small W");
        System.out.println("   - LCS input size grows polynomially with complexity");
        System.out.println("   - Knapsack complexity grows exponentially with log(W)");
        System.out.println("=".repeat(90));
    }

    public static void main(String[] args) {
        Item[] disasterSupplies = {
                new Item("Water Purification Kit", 8, 12000),
                new Item("Medical Emergency Pack", 12, 18000),
                new Item("Thermal Blankets (50x)", 15, 8000),
                new Item("High-Energy Food Rations", 10, 9000),
                new Item("Portable Generator", 25, 15000),
                new Item("First Aid Supplies", 6, 11000),
                new Item("Communication Radio", 5, 14000),
                new Item("Tents (5 family-size)", 20, 10000),
                new Item("Flashlights & Batteries", 7, 6000),
                new Item("Baby Formula & Diapers", 9, 13000),
                new Item("Surgical Instruments", 4, 16000),
                new Item("Antibiotics & Vaccines", 3, 20000),
                new Item("Solar Chargers (10x)", 6, 7000)
        };

        int helicopterCapacity = 80;

        demonstrateKnapsack(disasterSupplies, helicopterCapacity);
    }
}
