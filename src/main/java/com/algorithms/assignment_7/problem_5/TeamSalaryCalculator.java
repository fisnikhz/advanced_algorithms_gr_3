package com.algorithms.assignment_7.problem_5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TeamSalaryCalculator {
    private static int callCount = 0;

    public static int getCallCount() {
        return callCount;
    }

    public static void resetCallCount() {
        callCount = 0;
    }

    public static double calculateTeamSalary(List<Employee> employees, int managerId) {
        return calculateTeamSalary(employees, managerId, new HashMap<>());
    }

    public static double calculateTeamSalary(List<Employee> employees, int managerId, Map<Integer, Double> memo) {
        if (employees == null || employees.isEmpty()) {
            return 0;
        }
        Map<Integer, Employee> map = new HashMap<>();
        Map<Integer, List<Integer>> children = new HashMap<>();
        for (Employee employee : employees) {
            map.put(employee.getId(), employee);
            Integer supervisorId = employee.getSupervisorId();
            if (supervisorId != null) {
                children.computeIfAbsent(supervisorId, k -> new ArrayList<>()).add(employee.getId());
            }
        }
        if (!map.containsKey(managerId)) {
            return 0;
        }
        return dfs(managerId, map, children, memo, new HashSet<>());
    }

    private static double dfs(
            int managerId,
            Map<Integer, Employee> map,
            Map<Integer, List<Integer>> children,
            Map<Integer, Double> memo,
            Set<Integer> visiting
    ) {
        if (memo.containsKey(managerId)) {
            return memo.get(managerId);
        }
        if (!visiting.add(managerId)) {
            return 0;
        }
        callCount++;
        double total = map.get(managerId).getSalary();
        List<Integer> reports = children.get(managerId);
        if (reports != null) {
            for (Integer childId : reports) {
                total += dfs(childId, map, children, memo, visiting);
            }
        }
        visiting.remove(managerId);
        memo.put(managerId, total);
        return total;
    }
}

