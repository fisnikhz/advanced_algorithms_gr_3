package com.algorithms.assignment_7.problem_3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PathToCeoFinder {

    public static List<Integer> getPathToCeo(List<Employee> employees, int employeeId) {
        if (employees == null || employees.isEmpty()) {
            return Collections.emptyList();
        }

        Map<Integer, Employee> employeeMap = new HashMap<>();
        for (Employee employee : employees) {
            employeeMap.put(employee.getId(), employee);
        }

        if (!employeeMap.containsKey(employeeId)) {
            return Collections.emptyList();
        }

        Set<Integer> visiting = new HashSet<>();
        List<Integer> path = buildPath(employeeMap, employeeId, visiting);
        return path == null ? Collections.emptyList() : path;
    }

    private static List<Integer> buildPath(
            Map<Integer, Employee> employeeMap,
            int currentId,
            Set<Integer> visiting
    ) {
        if (!visiting.add(currentId)) {
            return null;
        }

        Employee current = employeeMap.get(currentId);
        if (current == null) {
            visiting.remove(currentId);
            return null;
        }

        List<Integer> path = new ArrayList<>();
        path.add(currentId);

        Integer supervisorId = current.getSupervisorId();
        if (supervisorId == null) {
            visiting.remove(currentId);
            return path;
        }

        List<Integer> supervisorPath = buildPath(employeeMap, supervisorId, visiting);
        visiting.remove(currentId);

        if (supervisorPath == null) {
            return null;
        }

        path.addAll(supervisorPath);
        return path;
    }
}

