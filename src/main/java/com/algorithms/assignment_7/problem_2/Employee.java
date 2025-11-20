package com.algorithms.assignment_7.problem_2;

public class Employee {
    private final int id;
    private final String name;
    private final String position;
    private final double salary;
    private Integer supervisorId;

    public Employee(int id, String name, String position, double salary, Integer supervisorId) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.salary = salary;
        this.supervisorId = supervisorId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public double getSalary() {
        return salary;
    }

    public Integer getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(Integer supervisorId) {
        this.supervisorId = supervisorId;
    }

    @Override
    public String toString() {
        return String.format("Employee{id=%d, name='%s', position='%s', salary=%.0f, supervisorId=%s}",
                id, name, position, salary, supervisorId);
    }
}

