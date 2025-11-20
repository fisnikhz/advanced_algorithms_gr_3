package com.algorithms.assignment_7.problem_4;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("=".repeat(60));
        System.out.println("Problem 4: Lowest Common Manager");
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
                12,
                9,
                new CommonManager(2, "Bob Smith", 3, 2)
        );
        runTest(
                employees,
                8,
                10,
                new CommonManager(4, "David Brown", 1, 1)
        );
        runTest(
                employees,
                1,
                12,
                new CommonManager(1, "Alice Johnson", 0, 4)
        );
    }

    private static void runEdgeCaseTests(List<Employee> employees) {
        System.out.println("EDGE CASE TESTS");
        System.out.println("-".repeat(60));
        runTest(
                employees,
                7,
                12,
                new CommonManager(7, "Grace Lee", 0, 1)
        );
        runTest(employees, 7, 999, null);
        List<Employee> cycleEmployees = Arrays.asList(
                new Employee(1, "A", "CEO", 100000, null),
                new Employee(2, "B", "Manager", 90000, 1),
                new Employee(3, "C", "Lead", 80000, 2)
        );
        cycleEmployees.get(1).setSupervisorId(3);
        runTest(cycleEmployees, 2, 3, null);
    }

    private static void runTest(List<Employee> employees,
                                int empId1,
                                int empId2,
                                CommonManager expected) {
        System.out.println("Employees: " + empId1 + " & " + empId2);
        CommonManager result = LowestCommonManagerFinder.findLowestCommonManager(employees, empId1, empId2);
        boolean passed = expected == null ? result == null : expected.equals(result);
        System.out.println(passed ? "[PASS]" : "[FAIL]");
        if (result == null) {
            System.out.println("Result: null");
        } else {
            System.out.println("Result: " + result);
        }
        if (!passed) {
            System.out.println("Expected: " + expected);
        }
        System.out.println();
    }
}

