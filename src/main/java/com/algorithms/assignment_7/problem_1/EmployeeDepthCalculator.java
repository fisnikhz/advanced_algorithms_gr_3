package com.algorithms.assignment_7.problem_1;

import java.util.*;

public class EmployeeDepthCalculator {

    public static int getEmployeeDepth(List<Employee> employees, int employeeId) {
        Map<Integer, Employee> employeeMap = new HashMap<>();
        for (Employee emp : employees) {
            employeeMap.put(emp.getId(), emp);
        }

        if (!employeeMap.containsKey(employeeId)) {
            return -1;
        }

        Set<Integer> visited = new HashSet<>();
        
        return calculateDepthRecursive(employeeMap, employeeId, visited);
    }

    private static int calculateDepthRecursive(Map<Integer, Employee> employeeMap, 
                                                int employeeId, 
                                                Set<Integer> visited) {
        if (visited.contains(employeeId)) {
            return -1;
        }

        Employee employee = employeeMap.get(employeeId);
        
        if (employee.getSupervisorId() == null) {
            return 0;
        }

        if (!employeeMap.containsKey(employee.getSupervisorId())) {
            return -1;
        }

        visited.add(employeeId);

        int supervisorDepth = calculateDepthRecursive(employeeMap, employee.getSupervisorId(), visited);

        visited.remove(employeeId);

        if (supervisorDepth == -1) {
            return -1;
        }

        return supervisorDepth + 1;
    }
}

