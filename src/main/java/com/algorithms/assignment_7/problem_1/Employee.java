package com.algorithms.assignment_7.problem_1;

public class Employee {
    private int id;
    private String name;
    private String position;
    private double salary;
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

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
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

