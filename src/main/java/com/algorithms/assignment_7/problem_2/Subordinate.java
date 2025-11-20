package com.algorithms.assignment_7.problem_2;

public record Subordinate(int id, String name, int relativeDepth) {

    @Override
    public String toString() {
        return String.format("Subordinate{id=%d, name='%s', relativeDepth=%d}", id, name, relativeDepth);
    }
}

