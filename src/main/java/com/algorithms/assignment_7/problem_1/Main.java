package com.algorithms.assignment_7.problem_1;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("=".repeat(60));
        System.out.println("Problem 1: Calculate Employee Depth");
        System.out.println("=".repeat(60));
        System.out.println();

        List<Employee> employees = createEmployeeData();

        testExample1(employees);
        testExample2(employees);
        testExample3(employees);
        testExample4(employees);
        testExample5(employees);
        
        testAdditionalCases(employees);
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

    private static void testExample1(List<Employee> employees) {
        System.out.println("TEST 1: CEO (Alice - id=1)");
        System.out.println("-".repeat(60));
        int depth = EmployeeDepthCalculator.getEmployeeDepth(employees, 1);
        System.out.println("Employee ID: 1 (Alice Johnson - CEO)");
        System.out.println("Result: " + depth);
        System.out.println("Expected: 0");
        System.out.println("Test Result: " + (depth == 0 ? "✓ PASSED" : "✗ FAILED"));
        System.out.println();
    }

    private static void testExample2(List<Employee> employees) {
        System.out.println("TEST 2: CTO (Bob - id=2)");
        System.out.println("-".repeat(60));
        int depth = EmployeeDepthCalculator.getEmployeeDepth(employees, 2);
        System.out.println("Employee ID: 2 (Bob Smith - CTO)");
        System.out.println("Result: " + depth);
        System.out.println("Expected: 1");
        System.out.println("Test Result: " + (depth == 1 ? "✓ PASSED" : "✗ FAILED"));
        System.out.println();
    }

    private static void testExample3(List<Employee> employees) {
        System.out.println("TEST 3: Senior Developer (Grace - id=7)");
        System.out.println("-".repeat(60));
        int depth = EmployeeDepthCalculator.getEmployeeDepth(employees, 7);
        System.out.println("Employee ID: 7 (Grace Lee - Senior Developer)");
        System.out.println("Result: " + depth);
        System.out.println("Expected: 3");
        System.out.println("Explanation: CEO(0) -> CTO(1) -> Engineering Manager(2) -> Senior Developer(3)");
        System.out.println("Test Result: " + (depth == 3 ? "✓ PASSED" : "✗ FAILED"));
        System.out.println();
    }

    private static void testExample4(List<Employee> employees) {
        System.out.println("TEST 4: Intern Developer (Liam - id=12)");
        System.out.println("-".repeat(60));
        int depth = EmployeeDepthCalculator.getEmployeeDepth(employees, 12);
        System.out.println("Employee ID: 12 (Liam Garcia - Intern Developer)");
        System.out.println("Result: " + depth);
        System.out.println("Expected: 4");
        System.out.println("Explanation: CEO(0) -> CTO(1) -> Engineering Manager(2) -> Senior Developer(3) -> Intern(4)");
        System.out.println("Test Result: " + (depth == 4 ? "✓ PASSED" : "✗ FAILED"));
        System.out.println();
    }

    private static void testExample5(List<Employee> employees) {
        System.out.println("TEST 5: Non-existent Employee (id=999)");
        System.out.println("-".repeat(60));
        int depth = EmployeeDepthCalculator.getEmployeeDepth(employees, 999);
        System.out.println("Employee ID: 999 (Not found)");
        System.out.println("Result: " + depth);
        System.out.println("Expected: -1");
        System.out.println("Test Result: " + (depth == -1 ? "✓ PASSED" : "✗ FAILED"));
        System.out.println();
    }

    private static void testAdditionalCases(List<Employee> employees) {
        System.out.println("ADDITIONAL TEST CASES:");
        System.out.println("=".repeat(60));
        System.out.println();

        System.out.println("Testing all employees:");
        System.out.println("-".repeat(60));
        Map<Integer, Integer> expectedDepths = new HashMap<>();
        expectedDepths.put(1, 0);
        expectedDepths.put(2, 1);
        expectedDepths.put(3, 1);
        expectedDepths.put(4, 2);
        expectedDepths.put(5, 2);
        expectedDepths.put(6, 2);
        expectedDepths.put(7, 3);
        expectedDepths.put(8, 3);
        expectedDepths.put(9, 3);
        expectedDepths.put(10, 3);
        expectedDepths.put(11, 3);
        expectedDepths.put(12, 4);

        for (Employee emp : employees) {
            int depth = EmployeeDepthCalculator.getEmployeeDepth(employees, emp.getId());
            int expected = expectedDepths.get(emp.getId());
            String status = (depth == expected) ? "✓" : "✗";
            System.out.printf("%s ID=%2d: %-30s Depth=%d (Expected=%d)%n",
                    status, emp.getId(), emp.getName(), depth, expected);
        }
        System.out.println();

        System.out.println("TEST: Cycle Detection");
        System.out.println("-".repeat(60));
        List<Employee> employeesWithCycle = Arrays.asList(
            new Employee(1, "Alice", "CEO", 100000, null),
            new Employee(2, "Bob", "Manager", 80000, 1),
            new Employee(3, "Carol", "Employee", 60000, 2)
        );

        List<Employee> cycleEmployees = new ArrayList<>(Arrays.asList(
            new Employee(1, "A", "CEO", 100000, null),
            new Employee(2, "B", "Manager", 80000, 1),
            new Employee(3, "C", "Employee", 60000, 2)
        ));
        cycleEmployees.get(1).setSupervisorId(3);
        
        int cycleDepth = EmployeeDepthCalculator.getEmployeeDepth(cycleEmployees, 3);
        System.out.println("Testing with circular reference (C -> B -> C)");
        System.out.println("Result: " + cycleDepth);
        System.out.println("Expected: -1 (cycle detected)");
        System.out.println("Test Result: " + (cycleDepth == -1 ? "✓ PASSED" : "✗ FAILED"));
        System.out.println();
    }
}

