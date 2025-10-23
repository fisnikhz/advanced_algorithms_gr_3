package com.algorithms.assignment_4;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class DNAAnalyzer {
    private Map<Integer, List<String>> patternHashMap;
    private static final int BASE = 4;
    private static final int PRIME = 101;

    public DNAAnalyzer(List<String> patterns) {
        this.patternHashMap = new HashMap<>();
        buildPatternHashMap(patterns);
    }

    private void buildPatternHashMap(List<String> patterns) {
        for (String pattern : patterns) {
            int hash = computeHash(pattern);
            patternHashMap.computeIfAbsent(hash, k -> new ArrayList<>()).add(pattern);
        }
    }

    private int computeHash(String str) {
        int hash = 0;
        for (int i = 0; i < str.length(); i++) {
            hash = (hash * BASE + charToValue(str.charAt(i))) % PRIME;
        }
        return hash;
    }

    private int charToValue(char c) {
        switch (c) {
            case 'A': return 0;
            case 'T': return 1;
            case 'G': return 2;
            case 'C': return 3;
            default: return 0;
        }
    }

    public AnalysisResult analyzeSequence(DNASequence dnaSequence) {
        List<String> foundMutations = new ArrayList<>();
        String sequence = dnaSequence.getSequence();
        Map<String, Boolean> foundMap = new HashMap<>();

        for (Map.Entry<Integer, List<String>> entry : patternHashMap.entrySet()) {
            List<String> patternsWithHash = entry.getValue();
            
            for (String pattern : patternsWithHash) {
                if (!foundMap.containsKey(pattern) && containsPatternRabinKarp(sequence, pattern)) {
                    foundMutations.add(pattern);
                    foundMap.put(pattern, true);
                }
            }
        }

        return new AnalysisResult(dnaSequence.getId(), foundMutations);
    }

    private boolean containsPatternRabinKarp(String text, String pattern) {
        int m = pattern.length();
        int n = text.length();
        
        if (m > n) return false;
        
        int patternHash = computeHash(pattern);
        int textHash = computeHash(text.substring(0, m));
        
        int h = 1;
        for (int i = 0; i < m - 1; i++) {
            h = (h * BASE) % PRIME;
        }
        
        for (int i = 0; i <= n - m; i++) {
            if (patternHash == textHash) {
                if (text.substring(i, i + m).equals(pattern)) {
                    return true;
                }
            }
            
            if (i < n - m) {
                textHash = (BASE * (textHash - charToValue(text.charAt(i)) * h) + charToValue(text.charAt(i + m))) % PRIME;
                if (textHash < 0) {
                    textHash = textHash + PRIME;
                }
            }
        }
        
        return false;
    }

    public List<AnalysisResult> analyzeBatch(List<DNASequence> sequences) {
        List<AnalysisResult> results = new ArrayList<>();
        for (DNASequence seq : sequences) {
            AnalysisResult result = analyzeSequence(seq);
            results.add(result);
        }
        return results;
    }

    public static class AnalysisResult {
        private String sequenceId;
        private List<String> mutationsFound;

        public AnalysisResult(String sequenceId, List<String> mutationsFound) {
            this.sequenceId = sequenceId;
            this.mutationsFound = mutationsFound;
        }

        public String getSequenceId() {
            return sequenceId;
        }

        public List<String> getMutationsFound() {
            return mutationsFound;
        }

        public boolean hasClean() {
            return mutationsFound.isEmpty();
        }

        @Override
        public String toString() {
            if (hasClean()) {
                return sequenceId + ": CLEAN";
            } else {
                return sequenceId + ": MUTATIONS FOUND - " + mutationsFound;
            }
        }
    }
}
