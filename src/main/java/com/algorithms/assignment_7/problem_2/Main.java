package com.algorithms.assignment_7.problem_2;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("=".repeat(60));
        System.out.println("Problem 2: Get All Subordinates");
        System.out.println("=".repeat(60));
        System.out.println();

        List<Employee> employees = createEmployeeData();

        runExampleTests(employees);
        runEdgeCaseTests(employees);
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

        runTest(
                employees,
                2,
                "CTO's hierarchy",
                List.of(
                        new Subordinate(4, "David Brown", 1),
                        new Subordinate(5, "Eve Davis", 1),
                        new Subordinate(7, "Grace Lee", 2),
                        new Subordinate(8, "Henry Martinez", 2),
                        new Subordinate(9, "Ivy Chen", 2),
                        new Subordinate(10, "Jack Thompson", 2),
                        new Subordinate(12, "Liam Garcia", 3)
                )
        );

        runTest(
                employees,
                6,
                "Senior accountant direct report",
                List.of(new Subordinate(11, "Kelly Anderson", 1))
        );

        runTest(
                employees,
                12,
                "Individual contributor with no reports",
                List.of()
        );
    }

    private static void runEdgeCaseTests(List<Employee> employees) {
        System.out.println("EDGE CASE TESTS");
        System.out.println("-".repeat(60));

        runTest(
                employees,
                1,
                "CEO should list all other employees with increasing depths.",
                List.of(
                        new Subordinate(2, "Bob Smith", 1),
                        new Subordinate(3, "Carol White", 1),
                        new Subordinate(4, "David Brown", 2),
                        new Subordinate(5, "Eve Davis", 2),
                        new Subordinate(6, "Frank Wilson", 2),
                        new Subordinate(7, "Grace Lee", 3),
                        new Subordinate(8, "Henry Martinez", 3),
                        new Subordinate(9, "Ivy Chen", 3),
                        new Subordinate(10, "Jack Thompson", 3),
                        new Subordinate(11, "Kelly Anderson", 3),
                        new Subordinate(12, "Liam Garcia", 4)
                )
        );
        runTest(
                employees,
                999,
                "Invalid manager ID should return empty list.",
                List.of()
        );
        runTest(
                employees,
                5,
                "Manager with both direct and indirect reports.",
                List.of(new Subordinate(9, "Ivy Chen", 1))
        );

        System.out.println("Cycle detection test");
        List<Employee> cycleEmployees = Arrays.asList(
                new Employee(1, "A", "CEO", 100000, null),
                new Employee(2, "B", "Manager", 90000, 1),
                new Employee(3, "C", "Lead", 80000, 2)
        );
        cycleEmployees.get(1).setSupervisorId(3);
        runTest(
                cycleEmployees,
                1,
                "Cycle should not cause infinite recursion.",
                List.of()
        );
    }

    private static void runTest(List<Employee> employees,
                               int managerId,
                               String description,
                               List<Subordinate> expected) {
        System.out.println("Manager ID: " + managerId);
        System.out.println(description);
        List<Subordinate> result = SubordinateFinder.getAllSubordinates(employees, managerId);
        boolean passed = expected.equals(result);
        System.out.println(passed ? "[PASS]" : "[FAIL]");
        if (result.isEmpty()) {
            System.out.println("Result: []");
        } else {
            result.forEach(sub -> System.out.println("  " + sub));
        }
        if (!passed) {
            System.out.println("Expected:");
            if (expected.isEmpty()) {
                System.out.println("  []");
            } else {
                expected.forEach(sub -> System.out.println("  " + sub));
            }
        }
        System.out.println();
    }
}

