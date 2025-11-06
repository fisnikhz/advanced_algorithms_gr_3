package com.algorithms.assignment_6;

public class Batch {
    private String id;
    private int size;
    private int priority;

    public Batch(String id, int size, int priority) {
        this.id = id;
        this.size = size;
        this.priority = priority;
    }

    public String getId() {
        return id;
    }

    public int getSize() {
        return size;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return String.format("Batch{id='%s', size=%d, priority=%d}", id, size, priority);
    }
}
