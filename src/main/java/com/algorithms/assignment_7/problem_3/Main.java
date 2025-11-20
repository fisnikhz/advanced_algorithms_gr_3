package com.algorithms.assignment_7.problem_3;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("=".repeat(60));
        System.out.println("Problem 3: Find Path to CEO");
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

        runTest(employees, 12, "Intern path to CEO", List.of(12, 7, 4, 2, 1));
        runTest(employees, 1, "CEO path to self", List.of(1));
        runTest(employees, 5, "QA manager path", List.of(5, 2, 1));
    }

    private static void runEdgeCaseTests(List<Employee> employees) {
        System.out.println("EDGE CASE TESTS");
        System.out.println("-".repeat(60));

        runTest(employees, 999, "Unknown employee should return empty path.", List.of());

        List<Employee> cycleEmployees = Arrays.asList(
                new Employee(1, "A", "CEO", 100000, null),
                new Employee(2, "B", "Manager", 90000, 1),
                new Employee(3, "C", "Lead", 80000, 2)
        );
        cycleEmployees.get(1).setSupervisorId(3); 
        runTest(cycleEmployees, 2, "Cycle should return empty path.", List.of());
    }

    private static void runTest(List<Employee> employees,
                               int employeeId,
                               String description,
                               List<Integer> expected) {
        System.out.println("Employee ID: " + employeeId);
        System.out.println("Description: " + description);
        List<Integer> path = PathToCeoFinder.getPathToCeo(employees, employeeId);
        boolean passed = expected.equals(path);
        System.out.println(passed ? "[PASS]" : "[FAIL]");
        System.out.println("Result: " + path);
        if (!passed) {
            System.out.println("Expected: " + expected);
        }
        System.out.println();
    }
}

