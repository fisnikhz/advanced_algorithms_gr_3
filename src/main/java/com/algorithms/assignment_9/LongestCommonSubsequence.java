package com.algorithms.assignment_9;

public class LongestCommonSubsequence {


    public static String findLCS(String seq1, String seq2) {
        int m = seq1.length();
        int n = seq2.length();

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (seq1.charAt(i - 1) == seq2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        StringBuilder lcs = new StringBuilder();
        int i = m, j = n;
        while (i > 0 && j > 0) {
            if (seq1.charAt(i - 1) == seq2.charAt(j - 1)) {
                lcs.insert(0, seq1.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }

        return lcs.toString();
    }

    public static void printDPTable(String seq1, String seq2) {
        int m = seq1.length();
        int n = seq2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (seq1.charAt(i - 1) == seq2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        System.out.println("\nDynamic Programming Table:");
        System.out.println("=".repeat(60));
        System.out.print("      ");
        for (char c : seq2.toCharArray()) {
            System.out.printf("%3c", c);
        }
        System.out.println();
        System.out.println("-".repeat(60));

        for (int i = 0; i <= m; i++) {
            if (i == 0) {
                System.out.print("    ");
            } else {
                System.out.printf("%3c ", seq1.charAt(i - 1));
            }
            for (int j = 0; j <= n; j++) {
                System.out.printf("%3d", dp[i][j]);
            }
            System.out.println();
        }
        System.out.println("=".repeat(60));
    }

    public static void demonstrateStepByStep(String seq1, String seq2) {
        System.out.println("LONGEST COMMON SUBSEQUENCE - STEP-BY-STEP SOLUTION");
        System.out.println("=".repeat(70));
        System.out.println("\nREAL-WORLD SCENARIO: DNA Sequence Analysis");
        System.out.println("Comparing genetic sequences to identify conserved regions\n");

        System.out.println("INPUT:");
        System.out.println("  Sequence 1 (Human):     " + seq1 + " (length: " + seq1.length() + ")");
        System.out.println("  Sequence 2 (Chimp):     " + seq2 + " (length: " + seq2.length() + ")");

        printDPTable(seq1, seq2);

        String lcs = findLCS(seq1, seq2);
        System.out.println("\nRESULT:");
        System.out.println("  Longest Common Subsequence: " + lcs);
        System.out.println("  Length: " + lcs.length());

        System.out.println("\nALIGNMENT VISUALIZATION:");
        highlightLCS(seq1, seq2, lcs);

        System.out.println("\nCOMPLEXITY ANALYSIS:");
        System.out.println("=".repeat(70));
        System.out.println("Time Complexity:  O(m × n) = O(" + seq1.length() + " × " + seq2.length() + ") = O("
                + (seq1.length() * seq2.length()) + ")");
        System.out.println("Space Complexity: O(m × n) for the DP table");
        System.out.println("\nWHY LCS BELONGS TO CLASS P:");
        System.out.println("=".repeat(70));
        System.out.println("1. Polynomial Time: The algorithm runs in O(m × n) time, which is");
        System.out.println("   polynomial in the input size.");
        System.out.println("2. Deterministic: The dynamic programming approach guarantees");
        System.out.println("   finding the optimal solution without guessing.");
        System.out.println("3. Optimal Substructure: The problem can be broken down into");
        System.out.println("   smaller subproblems whose solutions can be combined.");
        System.out.println("4. No Exponential Growth: Unlike NP problems, the solution space");
        System.out.println("   doesn't grow exponentially with input size.");
        System.out.println("=".repeat(70));
    }

    private static void highlightLCS(String seq1, String seq2, String lcs) {
        System.out.println("  Seq1: " + highlightSequence(seq1, lcs));
        System.out.println("  Seq2: " + highlightSequence(seq2, lcs));
        System.out.println("  LCS:  " + lcs + " (marked with *)");
    }


    private static String highlightSequence(String seq, String lcs) {
        StringBuilder result = new StringBuilder();
        int lcsIndex = 0;

        for (int i = 0; i < seq.length(); i++) {
            if (lcsIndex < lcs.length() && seq.charAt(i) == lcs.charAt(lcsIndex)) {
                result.append(seq.charAt(i)).append("*");
                lcsIndex++;
            } else {
                result.append(seq.charAt(i)).append(" ");
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        String sequence1 = "ACGTTAGCATGCTA"; 
        String sequence2 = "AGTCAGCGATGCT"; 

        demonstrateStepByStep(sequence1, sequence2);

        System.out.println("\n\n");
        System.out.println("BONUS EXAMPLE: Protein Sequence Comparison");
        System.out.println("=".repeat(70));
        String protein1 = "MSTVAELITYSAK"; 
        String protein2 = "MSTVGELITSAK"; 
        demonstrateStepByStep(protein1, protein2);
    }
}
