package com.algorithms.assignment_4;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Assignment4Main {
    private static final String DNA_DATA_PATH = "src/main/java/com/algorithms/assignment_4/data/dna_500.json";
    private static final String MUTATIONS_DATA_PATH = "src/main/java/com/algorithms/assignment_4/data/mutation_patterns.json";

    public static void main(String[] args) {
        try {
            List<String> mutationPatterns = loadMutationPatterns(MUTATIONS_DATA_PATH);
            List<DNASequence> dnaSequences = loadDNASequences(DNA_DATA_PATH);

            System.out.println("=== DNA Sequence Analysis - Gene Expression Patterns ===");
            System.out.println("Mutation patterns loaded: " + mutationPatterns.size());
            System.out.println("DNA sequences to analyze: " + dnaSequences.size());
            System.out.println("========================================================\n");

            DNAAnalyzer analyzer = new DNAAnalyzer(mutationPatterns);

            long startTime = System.currentTimeMillis();
            List<DNAAnalyzer.AnalysisResult> results = analyzer.analyzeBatch(dnaSequences);
            long endTime = System.currentTimeMillis();

            int sequencesWithMutations = 0;
            for (DNAAnalyzer.AnalysisResult result : results) {
                System.out.println(result);
                if (!result.hasClean()) {
                    sequencesWithMutations++;
                }
            }

            System.out.println("\n========================================================");
            System.out.println("Analysis Summary:");
            System.out.println("Total sequences analyzed: " + dnaSequences.size());
            System.out.println("Sequences with mutations: " + sequencesWithMutations);
            System.out.println("Clean sequences: " + (dnaSequences.size() - sequencesWithMutations));
            System.out.println("Processing time: " + (endTime - startTime) + " ms");
            System.out.println("========================================================");

        } catch (IOException e) {
            System.err.println("Error reading data files: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static List<String> loadMutationPatterns(String filePath) throws IOException {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(filePath)) {
            Type type = new TypeToken<Map<String, List<String>>>() {}.getType();
            Map<String, List<String>> data = gson.fromJson(reader, type);
            return data.get("mutation_patterns");
        }
    }

    private static List<DNASequence> loadDNASequences(String filePath) throws IOException {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(filePath)) {
            Type type = new TypeToken<Map<String, List<DNASequence>>>() {}.getType();
            Map<String, List<DNASequence>> data = gson.fromJson(reader, type);
            return data.get("dna_sequences");
        }
    }
}
