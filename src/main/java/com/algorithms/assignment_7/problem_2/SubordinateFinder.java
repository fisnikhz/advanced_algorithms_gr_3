package com.algorithms.assignment_7.problem_2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SubordinateFinder {

    public static List<Subordinate> getAllSubordinates(List<Employee> employees, int managerId) {
        Map<Integer, Employee> employeeMap = new HashMap<>();
        Map<Integer, List<Employee>> directReports = new HashMap<>();

        for (Employee employee : employees) {
            employeeMap.put(employee.getId(), employee);
            if (employee.getSupervisorId() != null) {
                directReports.computeIfAbsent(employee.getSupervisorId(), k -> new ArrayList<>()).add(employee);
            }
        }

        if (!employeeMap.containsKey(managerId)) {
            return new ArrayList<>();
        }

        List<Subordinate> result = new ArrayList<>();
        Set<Integer> path = new HashSet<>();
        path.add(managerId);
        dfsCollectSubordinates(managerId, 0, directReports, path, result);

        result.sort(Comparator
                .comparingInt(Subordinate::relativeDepth)
                .thenComparingInt(Subordinate::id));

        return result;
    }

    private static void dfsCollectSubordinates(int managerId,
                                               int depth,
                                               Map<Integer, List<Employee>> directReports,
                                               Set<Integer> path,
                                               List<Subordinate> result) {
        List<Employee> reports = directReports.get(managerId);
        if (reports == null) {
            return;
        }

        for (Employee report : reports) {
            if (!path.add(report.getId())) {
                continue;
            }

            int nextDepth = depth + 1;
            result.add(new Subordinate(report.getId(), report.getName(), nextDepth));
            dfsCollectSubordinates(report.getId(), nextDepth, directReports, path, result);
            path.remove(report.getId());
        }
    }
}

