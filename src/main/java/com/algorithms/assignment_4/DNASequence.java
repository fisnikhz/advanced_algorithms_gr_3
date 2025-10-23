package com.algorithms.assignment_4;

public class DNASequence {
    private String id;
    private String sequence;

    public DNASequence() {
    }

    public DNASequence(String id, String sequence) {
        this.id = id;
        this.sequence = sequence;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }
}
