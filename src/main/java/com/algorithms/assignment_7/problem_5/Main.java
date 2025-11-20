package com.algorithms.assignment_7.problem_5;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    private static final double EPSILON = 1e-6;

    public static void main(String[] args) {
        System.out.println("=".repeat(60));
        System.out.println("Problem 5: Team Salary with Memoization");
        System.out.println("=".repeat(60));
        System.out.println();
        List<Employee> employees = createEmployeeData();
        runExampleTests(employees);
        demonstrateMemoization(employees);
        runEdgeCases(employees);
    }

    private static List<Employee> createEmployeeData() {
        return Arrays.asList(
                new Employee(1, "Alice Johnson", "CEO", 250000, null),
                new Employee(2, "Bob Smith", "CTO", 180000, 1),
                new Employee(3, "Carol White", "CFO", 175000, 1),
                new Employee(4, "David Brown", "Engineering Manager", 140000, 2),
                new Employee(5, "Eve Davis", "QA Manager", 130000, 2),
                new Employee(6, "Frank Wilson", "Senior Accountant", 95000, 3),
                new Employee(7, "Grace Lee", "Senior Developer", 120000, 4),
                new Employee(8, "Henry Martinez", "Junior Developer", 85000, 4),
                new Employee(9, "Ivy Chen", "QA Engineer", 90000, 5),
                new Employee(10, "Jack Thompson", "DevOps Engineer", 110000, 4),
                new Employee(11, "Kelly Anderson", "Junior Accountant", 65000, 6),
                new Employee(12, "Liam Garcia", "Intern Developer", 50000, 7)
        );
    }

    private static void runExampleTests(List<Employee> employees) {
        System.out.println("EXAMPLE TESTS");
        System.out.println("-".repeat(60));
        runTest(employees, 1, 1490000);
        runTest(employees, 4, 505000);
        runTest(employees, 7, 170000);
        runTest(employees, 12, 50000);
        System.out.println();
    }

    private static void demonstrateMemoization(List<Employee> employees) {
        System.out.println("MEMOIZATION DEMO");
        System.out.println("-".repeat(60));
        Map<Integer, Double> memo = new HashMap<>();
        TeamSalaryCalculator.resetCallCount();
        double ceo = TeamSalaryCalculator.calculateTeamSalary(employees, 1, memo);
        System.out.println("CEO total: " + ceo + " | callCount=" + TeamSalaryCalculator.getCallCount());
        double cto = TeamSalaryCalculator.calculateTeamSalary(employees, 2, memo);
        System.out.println("CTO total: " + cto + " | callCount=" + TeamSalaryCalculator.getCallCount());
        double engMgr = TeamSalaryCalculator.calculateTeamSalary(employees, 4, memo);
        System.out.println("Engineering Manager total: " + engMgr + " | callCount=" + TeamSalaryCalculator.getCallCount());
        double dev = TeamSalaryCalculator.calculateTeamSalary(employees, 7, memo);
        System.out.println("Senior Developer total: " + dev + " | callCount=" + TeamSalaryCalculator.getCallCount());
        System.out.println("Memo entries: " + memo.keySet());
        System.out.println();
    }

    private static void runEdgeCases(List<Employee> employees) {
        System.out.println("EDGE CASES");
        System.out.println("-".repeat(60));
        runTest(employees, 999, 0);
        List<Employee> cycleEmployees = Arrays.asList(
                new Employee(1, "A", "CEO", 100000, null),
                new Employee(2, "B", "Manager", 90000, 1),
                new Employee(3, "C", "Lead", 80000, 2)
        );
        cycleEmployees.get(1).setSupervisorId(3);
        double result = TeamSalaryCalculator.calculateTeamSalary(cycleEmployees, 1);
        System.out.println("Cycle test total: " + result);
        System.out.println();
    }

    private static void runTest(List<Employee> employees, int managerId, double expected) {
        double total = TeamSalaryCalculator.calculateTeamSalary(employees, managerId);
        boolean passed = Math.abs(total - expected) < EPSILON;
        System.out.println(passed ? "[PASS]" : "[FAIL]");
        System.out.println("Manager " + managerId + " total: " + total + " | expected: " + expected);
        if (!passed) {
            System.out.println("Difference: " + (total - expected));
        }
        System.out.println();
    }
}

